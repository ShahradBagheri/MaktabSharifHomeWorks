package firstQuestion.repository.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.model.Student;
import firstQuestion.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    @Override
    public Student save(Student student) {

        logger.info("attempting to save in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.persist(student);
        return student;
    }

    @Override
    public void update(Student student) {

        logger.info("attempting to update in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.merge(student);
    }

    @Override
    public void delete(Student student) {

        logger.info("attempting to delete in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.remove(student);
    }

    @Override
    public Student loadById(Long id) {

        logger.info("attempting to load by id in {}", PersonRepositoryImpl.class.getSimpleName());
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> loadAll() {

        logger.info("attempting to loadAll in {}", PersonRepositoryImpl.class.getSimpleName());
        String hql = "FROM Student";
        TypedQuery<Student> query = entityManager.createQuery(hql,Student.class);
        return query.getResultList();
    }

    @Override
    public boolean contains(Student student) {

        logger.info("attempting to check contains in {}", PersonRepositoryImpl.class.getSimpleName());
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
