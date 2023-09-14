package service.impl;

import enumeration.Degree;
import enumeration.LoanType;
import enumeration.UniversityType;
import model.Loan;
import model.Payment;
import model.Student;
import repository.LoanRepository;
import service.LoanService;
import util.ApplicationContext;
import util.Constant;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoanServiceImpl implements LoanService {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final LoanRepository loanRepository = ApplicationContext.loanRepository;

    @Override
    public Loan create(Loan loan) {

        List<Payment> payments = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            Payment payment = Payment.builder()
                    .amount(getLoanPaymentAmount(i,loan.getLoanType(),loan.getStudent().getDegree(),loan.getStudent()))
                    .isPaid(false)
                    .dateToPay(getDateToPay(i,loan.getStudent().getEntranceYear(),loan.getStudent().getDegree()))
                    .loan(loan)
                    .build();
            payments.add(payment);
        }

        loan.setPayments(payments);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            loanRepository.create(loan);

            entityTransaction.commit();
            return loan;
        }catch (Exception e){
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public Loan update(Loan loan) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            loanRepository.update(loan);

            entityTransaction.commit();
            return loan;
        }catch (Exception e){
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public Loan findById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            Loan loan = loanRepository.findById(id);

            entityTransaction.commit();
            return loan;
        }catch (Exception e){
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public List<Loan> findByStudent(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            List<Loan> loans = loanRepository.findByStudent(student);

            entityTransaction.commit();
            return loans;
        }catch (Exception e){
            entityTransaction.rollback();
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    public boolean gotStudyLoanThisSemester(Student student) {
        List<Loan> loans = findByStudent(student);

        if (loans == null)
            return false;

        long count = loans.stream()
                .filter(loan -> loan.getLoanType() == LoanType.STUDYING)
                .map(Loan::getSubmitDate)
                .filter(this::dateIsThisSemester)
                .count();

        return count != 0;
    }

    @Override
    public boolean gotTuitionFeeLoanThisSemester(Student student) {
        List<Loan> loans = findByStudent(student);

        if (loans == null)
            return false;

        long count = loans.stream()
                .filter(loan -> loan.getLoanType() == LoanType.TUITION_FEE)
                .map(Loan::getSubmitDate)
                .filter(this::dateIsThisSemester)
                .count();

        return count != 0;
    }

    @Override
    public boolean gotRentLoan(Student student) {
        List<Loan> loans = findByStudent(student);

        if (loans == null)
            return false;

        long count = loans.stream()
                .filter(loan -> loan.getLoanType() == LoanType.RENT)
                .count();

        return count != 0;
    }

    @Override
    public boolean canGetTuitionFeeLoan(Student student) {
        return student.getUniversityType() == UniversityType.PUBLIC_NIGHT
                || student.getUniversityType() == UniversityType.PRIVATE
                || student.getUniversityType() == UniversityType.PARDIS
                || student.getUniversityType() == UniversityType.PAYAM_NOOR
                || student.getUniversityType() == UniversityType.ELMI_CARBORDI
                || student.getUniversityType() == UniversityType.AZAD;
    }

    @Override
    public boolean isTimeToGetLoan() {
        LocalDate todayDate = Constant.DATE;
        return (!todayDate.isBefore(LocalDate.of(todayDate.getYear(),8,1)) && !todayDate.isAfter(LocalDate.of(todayDate.getYear(),8,8)))
                || (!todayDate.isBefore(LocalDate.of(todayDate.getYear(),11,25)) && !todayDate.isAfter(LocalDate.of(todayDate.getYear(),12,2)));
    }

    @Override
    public boolean dateIsThisSemester(LocalDate localDate) {
        return (localDate.getYear() == Constant.DATE.getYear() && localDate.getMonthValue() == Constant.DATE.getMonthValue())
                || (localDate.getYear() == Constant.DATE.getYear()) && (localDate.getMonthValue() == Constant.DATE.getMonthValue() + 1);
    }

    @Override
    public boolean livesInBigCIty(Student student) {
        return (Constant.BIG_CITIES.contains(student.getRentContract().getCity()));
    }

    @Override
    public boolean spouseGotRentLoan(Student student) {
        List<Loan> loans = findByStudent(student.getSpouse());

        if (loans == null)
            return false;

        long count = loans.stream()
                .filter(loan -> loan.getLoanType() == LoanType.RENT)
                .count();

        return count != 0;
    }

    private double getLoanPaymentAmount(int numberPayment, LoanType loanType, Degree degree, Student student){

        double fullAmount = 0;

        final boolean secondTier = degree == Degree.MASTERS_CONNECTED || degree == Degree.MASTERS_NOT_CONNECTED || degree == Degree.PROFESSIONAL_PHD || degree == Degree.PHD_CONNECTED;
        final boolean firstTier = degree == Degree.COLLAGE || degree == Degree.BACHELOR_CONNECTED || degree == Degree.BACHELOR_NOT_CONNECTED;

        switch (loanType){
            case TUITION_FEE -> {
                if (firstTier)
                    fullAmount = 1_900_000;
                else if (secondTier)
                    fullAmount = 2_250_000;
                else
                    fullAmount = 2_600_000;
            }
            case STUDYING -> {
                if (firstTier)
                    fullAmount = 1_300_000;
                else if (secondTier)
                    fullAmount = 2_600_000;
                else
                    fullAmount = 65_000_000;
            }
            case RENT -> {
                if(student.getRentContract().getCity().equals("tehran"))
                    fullAmount = 32_000_000;
                else if (Constant.BIG_CITIES.contains(student.getRentContract().getCity()))
                    fullAmount = 26_000_000;
                else
                    fullAmount = 19_500_000;
            }
        }

        fullAmount = fullAmount + fullAmount/100*4;
        double oneStep = fullAmount/21;

        return oneStep*numberPayment;
    }

    private LocalDate getDateToPay(int numberPayment, int entranceYear, Degree degree){

        if (Constant.DATE.getMonthValue() + numberPayment-1 > 12){
            entranceYear += 1;
            numberPayment -= 12;
        }

        switch (degree){
            case COLLAGE, MASTERS_NOT_CONNECTED -> {
                return LocalDate.of(entranceYear + 2,Constant.DATE.getMonthValue() + numberPayment-1,Constant.DATE.getDayOfMonth());
            }
            case BACHELOR_CONNECTED, BACHELOR_NOT_CONNECTED -> {
                return LocalDate.of(entranceYear + 4,Constant.DATE.getMonthValue() + numberPayment-1,Constant.DATE.getDayOfMonth());
            }
            case MASTERS_CONNECTED -> {
                return LocalDate.of(entranceYear + 6,Constant.DATE.getMonthValue() + numberPayment-1,Constant.DATE.getDayOfMonth());
            }
            case PHD_CONNECTED, PHD_NOT_CONNECTED, PROFESSIONAL_PHD -> {
                return LocalDate.of(entranceYear + 5, Constant.DATE.getMonthValue() + numberPayment-1,Constant.DATE.getDayOfMonth());
            }
            default -> {
                return null;
            }
        }
    }
}
