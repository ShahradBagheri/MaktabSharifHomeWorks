package ui;

import enumeration.BankName;
import enumeration.LoanType;
import model.Card;
import model.Loan;
import model.RentContract;
import model.Student;
import util.ApplicationContext;
import util.Constant;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class GetLoanPanel {

    static Scanner scanner = new Scanner(System.in);

    public static void getLoan(Student student){

        if(!ApplicationContext.loanService.isTimeToGetLoan()){
            System.out.println("its not time to get a loan yet!");
            return;
        }

        System.out.println("what loan do you want to get\n1.tuition fee\n2.studying\n3.rent");
        switch (scanner.nextLine()){
            case "1" -> getTuitionFeeLoan(student);
            case "2" -> getStudyingLoan(student);
            case "3" -> getRentLoan(student);
            default -> System.out.println("not a valid option");
        }
    }

    public static void getStudyingLoan(Student student){

        if (ApplicationContext.loanService.gotStudyLoanThisSemester(student)){
            System.out.println("you already got a studying loan this semester");
            return;
        }

        if(student.getCard() == null){
            setCardMenu(student);
            student = ApplicationContext.studentService.findById(student.getId());
            if(student.getCard() == null){
                System.out.println("failed to add card");
                return;
            }
        }

        Loan loan = Loan.builder()
                .loanType(LoanType.STUDYING)
                .submitDate(Constant.DATE)
                .student(student)
                .build();

        loan = ApplicationContext.loanService.create(loan);
        if(loan == null)
            System.out.println("failed to get loan");
        else
            System.out.println("loan added");
    }

    public static void getTuitionFeeLoan(Student student){

        if (!ApplicationContext.loanService.canGetTuitionFeeLoan(student)){
            System.out.println("you cant get tuition fee loans");
            return;
        }

        if(ApplicationContext.loanService.gotTuitionFeeLoanThisSemester(student)){
            System.out.println("you already got a tuition fee loan this semester");
            return;
        }

        if(student.getCard() == null){
            setCardMenu(student);
            student = ApplicationContext.studentService.findById(student.getId());
            if(student.getCard() == null){
                System.out.println("failed to add card");
                return;
            }
        }

        Loan loan = Loan.builder()
                .loanType(LoanType.TUITION_FEE)
                .submitDate(Constant.DATE)
                .student(student)
                .build();

        loan = ApplicationContext.loanService.create(loan);
        if(loan == null)
            System.out.println("failed to get loan");
        else
            System.out.println("loan added");
    }

    public static void getRentLoan(Student student){

        if(ApplicationContext.loanService.gotRentLoan(student)){
            System.out.println("you already got a rent loan");
            return;
        }

        if(student.getRentContract() == null){

            System.out.println("enter city name");
            String city = scanner.nextLine();

            System.out.println("enter address");
            String address = scanner.nextLine();

            System.out.println("enter contract number");
            String contractNumber = scanner.nextLine();
            if(!ApplicationContext.rentContractService.validContractNumber(contractNumber)){
                System.out.println("not a valid contract number");
                return;
            }

            RentContract rentContract = RentContract.builder()
                    .city(city)
                    .address(address)
                    .contractNumber(contractNumber)
                    .build();

            student.setRentContract(rentContract);
            student = ApplicationContext.studentService.update(student);

            if(student.getRentContract() != null) {
                System.out.println("contract added");
            }
            else{
                System.out.println("failed to add contract");
                return;
            }
        }


        if(student.getSpouse() == null){
            Student spouse = null;

            boolean choosingSpouse = true;
            while (choosingSpouse){
                System.out.println("do you have a spouse?\nYes\nNo");
                switch (scanner.nextLine()){
                    case "Yes" -> {
                        System.out.println("login with your spouse account here\ngovernment id");
                        String governmentId = scanner.nextLine();

                        System.out.println("password");
                        String password = scanner.nextLine();

                        spouse = ApplicationContext.studentService.login(governmentId,password);
                        if(spouse == null){
                            System.out.println("wrong password or government id\nCouldn't add spouse to your account");
                        }else {
                            choosingSpouse = false;
                        }
                    }
                    case "No" -> choosingSpouse = false;

                    default -> System.out.println("not a valid option+");
                }

            }
            student.setSpouse(spouse);
            student = ApplicationContext.studentService.update(student);
            if(spouse != null){
                spouse.setSpouse(student);
                ApplicationContext.studentService.update(spouse);
            }
        }

        if(ApplicationContext.loanService.livesInBigCIty(student) && student.getSpouse() == null){
            System.out.println("you have to be married to get a rent loan in your city");
            return;
        }

        if(ApplicationContext.loanService.spouseGotRentLoan(student)){
            System.out.println("your spouse already got a rent loan");
            return;
        }

        if(student.getCard() == null){
            setCardMenu(student);
            student = ApplicationContext.studentService.findById(student.getId());
            if(student.getCard() == null){
                System.out.println("failed to add card");
                return;
            }
        }

        Loan loan = Loan.builder()
                .loanType(LoanType.RENT)
                .submitDate(Constant.DATE)
                .student(student)
                .build();

        loan = ApplicationContext.loanService.create(loan);
        if(loan == null)
            System.out.println("failed to get loan");
        else
            System.out.println("loan added");
    }

    public static void setCardMenu(Student student){

        Card card;

        boolean choosingBank = true;
        BankName bankName = null;
        while (choosingBank){
            System.out.println("choose bank\n1.Meli\n2.Refah\n3.Tejarat\n4.Maskan");
            switch (scanner.nextLine()){
                case "1" -> {
                    bankName = BankName.MELI;
                    choosingBank = false;
                }
                case "2" -> {
                    bankName = BankName.REFAH;
                    choosingBank = false;
                }
                case "3" -> {
                    bankName = BankName.TEJARAT;
                    choosingBank = false;
                }
                case "4" -> {
                    bankName = BankName.MASKAN;
                    choosingBank = false;
                }
                default -> System.out.println("not a valid option");
            }
        }

        System.out.println("Enter card number");
        String cardNumber = scanner.nextLine();
        if (!ApplicationContext.cardService.validCardNumber(cardNumber)){
            System.out.println("not a valid card number");
            return;
        }

        System.out.println("Enter cvv2");
        String cvv2 = scanner.nextLine();
        if(!ApplicationContext.cardService.validCvv2(cvv2)){
            System.out.println("not a valid cvv2");
            return;
        }

        System.out.println("expiration date (yyyy-mm-dd)");
        LocalDate expirationDate;

        try{
            expirationDate = LocalDate.parse(scanner.nextLine());
        }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            return;
        }

        card = Card.builder()
                .student(student)
                .bankName(bankName)
                .cardNumber(cardNumber)
                .cvv2(cvv2)
                .expirationDate(expirationDate)
                .build();

        student.setCard(card);
        ApplicationContext.studentService.update(student);
    }
}
