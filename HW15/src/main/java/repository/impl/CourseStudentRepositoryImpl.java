package repository.impl;

import model.CourseStudent;
import model.Student;
import repository.CourseStudentRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseStudentRepositoryImpl implements CourseStudentRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public CourseStudent create(CourseStudent courseStudent) {
        entityManager.persist(courseStudent);
        return courseStudent;
    }

    @Override
    public CourseStudent update(CourseStudent courseStudent) {
        entityManager.merge(courseStudent);
        return courseStudent;
    }

    @Override
    public void delete(CourseStudent courseStudent) {
        entityManager.remove(courseStudent);
    }

    @Override
    public List<CourseStudent> findCourseByStudent(Student student) {
        String jpql = "SELECT c FROM CourseStudent c WHERE c.student = :student";
        TypedQuery<CourseStudent> typedQuery = entityManager.createQuery(jpql,CourseStudent.class);
        typedQuery.setParameter("student",student);
        return typedQuery.getResultList();
    }

    @Override
    public CourseStudent findById(Long id) {
        return entityManager.find(CourseStudent.class, id);
    }
}
