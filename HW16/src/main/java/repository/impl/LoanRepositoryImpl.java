package repository.impl;

import model.Loan;
import model.Student;
import repository.LoanRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoanRepositoryImpl implements LoanRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Loan create(Loan loan) {

        entityManager.persist(loan);
        return loan;
    }

    @Override
    public Loan update(Loan loan) {

        entityManager.merge(loan);
        return loan;
    }


    @Override
    public Loan findById(Long id) {

        return entityManager.find(Loan.class, id);
    }

    @Override
    public List<Loan> findByStudent(Student student) {

        String jpql = "SELECT l FROM Loan l where l.student = :student";
        TypedQuery<Loan> typedQuery = entityManager.createQuery(jpql,Loan.class);
        typedQuery.setParameter("student", student);
        return typedQuery.getResultList();
    }
}
