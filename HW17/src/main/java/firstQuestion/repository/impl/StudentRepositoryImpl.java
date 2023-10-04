package firstQuestion.repository.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.connection.SessionFactorySingleton;
import firstQuestion.model.Student;
import firstQuestion.repository.StudentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Student save(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void delete(Student student) {
        entityManager.remove(student);
    }

    @Override
    public Student loadById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> loadAll() {
        String hql = "FROM Student";
        TypedQuery<Student> query = entityManager.createQuery(hql,Student.class);
        return query.getResultList();
    }

    @Override
    public boolean contains(Student student) {
        String hql = "SELECT COUNT(*) FROM Student s WHERE s.id = :studentId AND s.firstname = :studentFirstname AND s.lastname = :studentLastname AND s.birthdate = :studentBirthdate AND s.major = :studentMajor AND s.studentId = :studentStudentId AND s.admissionYear = :studentAdmissionYear";
        TypedQuery<Long> query = entityManager.createQuery(hql,Long.class);
        query.setParameter("studentFirstname",student.getFirstname());
        query.setParameter("studentLastname",student.getLastname());
        query.setParameter("studentId",student.getId());
        query.setParameter("studentBirthdate",student.getBirthdate());
        query.setParameter("studentMajor",student.getMajor());
        query.setParameter("studentStudentId",student.getStudentId());
        query.setParameter("studentAdmissionYear",student.getAdmissionYear());

        Long result = query.getSingleResult();
        return result != 0;
    }
}
