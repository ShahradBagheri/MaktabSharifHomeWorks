package repository.impl;

import model.Student;
import model.User;
import repository.StudentRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        String hql = "SELECT s FROM Student s";
        TypedQuery<Student> typedQuery = entityManager.createQuery(hql, Student.class);
        return typedQuery.getResultList();
    }

    @Override
    public Student findByUsername(String username) {
        String hql = "SELECT s FROM Student s WHERE s.username = :username";
        TypedQuery<Student> typedQuery = entityManager.createQuery(hql, Student.class);
        typedQuery.setParameter("username",username);
        return typedQuery.getSingleResult();
    }
}
