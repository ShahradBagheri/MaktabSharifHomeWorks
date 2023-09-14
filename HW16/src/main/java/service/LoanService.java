package service;

import model.Loan;
import model.Payment;
import model.Student;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {

    Loan create(Loan loan);

    Loan update(Loan loan);

    void delete(Loan loan);

    Loan findById(Long id);

    List<Loan> findByStudent(Student student);

    boolean gotStudyLoanThisSemester(Student student);

    boolean gotTuitionFeeLoanThisSemester(Student student);

    boolean gotRentLoan(Student student);

    boolean canGetTuitionFeeLoan(Student student);

    boolean isTimeToGetLoan();

    boolean dateIsThisSemester(LocalDate localDate);

    boolean livesInBigCIty(Student student);

    boolean spouseGotRentLoan(Student student);
}
