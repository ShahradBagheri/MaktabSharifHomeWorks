package repository;

import model.Loan;
import model.Student;

import java.util.List;

public interface LoanRepository {

    Loan create(Loan loan);

    Loan update(Loan loan);


    Loan findById(Long id);

    List<Loan> findByStudent(Student student);
}
