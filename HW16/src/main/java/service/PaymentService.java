package service;

import model.Payment;
import model.Student;

import java.util.List;

public interface PaymentService {
    Payment update(Payment payment);

    Payment findById(Long id);

    List<Payment> getPaidPayments(Student student);

    List<Payment> getUnpaidPayments(Student student);

    boolean studentOwnsPayment(Student student,Long id);

    boolean isPaid(Long id);
}
