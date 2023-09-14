package service.impl;

import model.Payment;
import model.Student;
import repository.PaymentRepository;
import service.PaymentService;
import util.ApplicationContext;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final PaymentRepository paymentRepository = ApplicationContext.paymentRepository;

    @Override
    public Payment update(Payment payment) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            paymentRepository.update(payment);

            entityTransaction.commit();
            return payment;
        }catch (Exception e){
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public Payment findById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            Payment payment = paymentRepository.findById(id);

            entityTransaction.commit();
            return payment;
        }catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public List<Payment> getPaidPayments(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            List<Payment> payments = paymentRepository.getPaidPayments(student);

            entityTransaction.commit();
            return payments;
        }catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public List<Payment> getUnpaidPayments(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try{
            entityTransaction.begin();

            List<Payment> payments = paymentRepository.getUnpaidPayments(student);

            entityTransaction.commit();
            return payments;
        }catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public boolean studentOwnsPayment(Student student, Long id) {
        Payment payment = findById(id);
        if(payment != null){
            return payment.getLoan().getStudent() == student;
        }
        return false;
    }

    @Override
    public boolean isPaid(Long id) {
        return findById(id).getIsPaid();
    }
}
