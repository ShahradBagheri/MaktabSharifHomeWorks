package repository.impl;

import model.Payment;
import model.Student;
import repository.PaymentRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Payment update(Payment payment) {
        entityManager.merge(payment);
        return payment;
    }

    @Override
    public Payment findById(Long id) {
        return entityManager.find(Payment.class, id);
    }

    @Override
    public List<Payment> findByStudent(Student student) {
        String jpql = "SELECT p FROM Payment p WHERE p.loan.student = :student ";
        TypedQuery<Payment> typedQuery = entityManager.createQuery(jpql,Payment.class);
        typedQuery.setParameter("student", student);
        return typedQuery.getResultList();
    }

    @Override
    public List<Payment> getPaidPayments(Student student) {
        String jpql = "SELECT p FROM Payment p WHERE p.loan.student = :student AND p.isPaid = true ";
        TypedQuery<Payment> typedQuery = entityManager.createQuery(jpql,Payment.class);
        typedQuery.setParameter("student", student);
        return typedQuery.getResultList();
    }

    @Override
    public List<Payment> getUnpaidPayments(Student student) {
        String jpql = "SELECT p FROM Payment p WHERE p.loan.student = :student AND p.isPaid = false ";
        TypedQuery<Payment> typedQuery = entityManager.createQuery(jpql,Payment.class);
        typedQuery.setParameter("student", student);
        return typedQuery.getResultList();
    }
}
