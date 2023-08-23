package repository.impl;

import model.Course;
import model.Professor;
import repository.CourseRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Course create(Course course) {
        entityManager.persist(course);
        return course;
    }

    @Override
    public Course update(Course course) {
        entityManager.merge(course);
        return course;
    }

    @Override
    public void delete(Course course) {
        entityManager.remove(course);
    }

    @Override
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> findAll() {
        String jpql = "SELECT c FROM Course c";
        TypedQuery<Course> typedQuery = entityManager.createQuery(jpql, Course.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Course> findByTerm(Long term) {
        String jpql = "SELECT c FROM Course c WHERE c.term = :term";
        TypedQuery<Course> typedQuery = entityManager.createQuery(jpql, Course.class);
        typedQuery.setParameter("term",term);
        return typedQuery.getResultList();
    }

    @Override
    public List<Course> findByProfessorAndTerm(Professor professor,Long term) {
        String jpql = "SELECT c FROM Course c WHERE c.professor = :professor AND c.term = :term";
        TypedQuery<Course> typedQuery = entityManager.createQuery(jpql, Course.class);
        typedQuery.setParameter("professor",professor);
        typedQuery.setParameter("term",term);
        return typedQuery.getResultList();
    }
}
