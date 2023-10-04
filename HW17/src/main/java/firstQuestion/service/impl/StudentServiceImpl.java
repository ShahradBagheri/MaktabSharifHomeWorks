package firstQuestion.service.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.connection.SessionFactorySingleton;
import firstQuestion.model.Student;
import firstQuestion.repository.impl.StudentRepositoryImpl;
import firstQuestion.service.StudentService;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Student save(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Student studentOut = studentRepository.save(student);

            entityTransaction.commit();
            return studentOut;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public void update(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            studentRepository.update(student);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public void delete(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            studentRepository.delete(student);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public Student loadById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Student student = studentRepository.loadById(id);

            entityTransaction.commit();

            return student;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public List<Student> loadAll() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            List<Student> students = studentRepository.loadAll();

            entityTransaction.commit();

            return students;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public boolean contains(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            boolean contains = studentRepository.contains(student);

            entityTransaction.commit();

            return contains;
        } catch (Exception e) {
            entityTransaction.rollback();
            return false;
        }
    }

    @Override
    public Student signUp(String firstname, String lastname, String studentId, String admissionYear) {
        Student student = new Student();
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setStudentId(studentId);
        student.setAdmissionYear(admissionYear);
        return save(student);
    }
}
