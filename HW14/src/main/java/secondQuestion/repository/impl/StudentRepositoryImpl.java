package secondQuestion.repository.impl;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import secondQuestion.connection.SessionFactorySingleton;
import secondQuestion.model.Student;
import secondQuestion.repository.StudentRepository;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Student save(Student student) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(student);
        Student savedStudent = session.get(Student.class,id);
        transaction.commit();
        return savedStudent;
    }

    @Override
    public void update(Student student) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
    }

    @Override
    public void delete(Student student) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(student);
        transaction.commit();
    }

    @Override
    public Student loadById(Long id) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class,id);
        transaction.commit();
        return student;
    }

    @Override
    public List<Student> loadAll() {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Student";
        Query<Student> query = session.createQuery(hql,Student.class);
        List<Student> result = query.list();
        transaction.commit();
        return result;
    }

    @Override
    public boolean contains(Student student) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT COUNT(*) FROM Student s WHERE s.id = :studentId AND s.firstname = :studentFirstname AND s.lastname = :studentLastname AND s.birthdate = :studentBirthdate AND s.major = :studentMajor AND s.studentId = :studentStudentId AND s.admissionYear = :studentAdmissionYear";
        Query<Long> query = session.createQuery(hql,Long.class);
        query.setParameter("studentFirstname",student.getFirstname());
        query.setParameter("studentLastname",student.getLastname());
        query.setParameter("studentId",student.getId());
        query.setParameter("studentBirthdate",student.getBirthdate());
        query.setParameter("studentMajor",student.getMajor());
        query.setParameter("studentStudentId",student.getStudentId());
        query.setParameter("studentAdmissionYear",student.getAdmissionYear());

        Long result = query.getSingleResult();
        transaction.commit();
        return result != 0;
    }
}
