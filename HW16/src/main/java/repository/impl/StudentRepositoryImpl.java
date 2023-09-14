package repository.impl;

import model.Student;
import repository.StudentRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class StudentRepositoryImpl implements StudentRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Student create(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    public Student update(Student student) {
        entityManager.merge(student);
        return student;
    }

    @Override
    public void delete(Student student) {
        entityManager.remove(student);
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student findByGovernmentId(String governmentId) {
        String jpql = "SELECT s FROM Student s WHERE s.governmentId = :governmentId";
        TypedQuery<Student> typedQuery = entityManager.createQuery(jpql, Student.class);
        typedQuery.setParameter("governmentId", governmentId);
        return typedQuery.getSingleResult();
    }

    @Override
    public boolean socialIdExists(String socialId) {
        String jpql = "SELECT count(s) FROM Student s WHERE s.socialId = :socialId";
        TypedQuery<Long> typedQuery = entityManager.createQuery(jpql, Long.class);
        typedQuery.setParameter("socialId", socialId);
        return typedQuery.getSingleResult() > 0;
    }

    @Override
    public boolean governmentIdExists(String governmentId) {
        String jpql = "SELECT count(s) FROM Student s WHERE s.governmentId = :governmentId";
            TypedQuery<Long> typedQuery = entityManager.createQuery(jpql, Long.class);
        typedQuery.setParameter("governmentId", governmentId);
        return typedQuery.getSingleResult() > 0;
    }

    @Override
    public boolean studentIdExists(String studentId) {
        String jpql = "SELECT count(s) FROM Student s WHERE s.studentId = :studentId";
        TypedQuery<Long> typedQuery = entityManager.createQuery(jpql, Long.class);
        typedQuery.setParameter("studentId", studentId);
        return typedQuery.getSingleResult() > 0;
    }
}
