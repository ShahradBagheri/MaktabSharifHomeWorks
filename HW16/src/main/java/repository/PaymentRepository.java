package repository;

import model.Payment;
import model.Student;

import java.util.List;

public interface PaymentRepository {

    Payment update(Payment payment);

    Payment findById(Long id);

    List<Payment> findByStudent(Student student);

    List<Payment> getPaidPayments(Student student);

    List<Payment> getUnpaidPayments(Student student);
}
