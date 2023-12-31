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

    @Override
    public List<CourseStudent> findCourseStudentByTermAndStudent(Long term, Student student) {
        String jpql = "SELECT c FROM CourseStudent c WHERE c.student = :student AND c.course.term = :currentTerm";
        TypedQuery<CourseStudent> typedQuery = entityManager.createQuery(jpql,CourseStudent.class);
        typedQuery.setParameter("student",student);
        typedQuery.setParameter("currentTerm",term);
        return typedQuery.getResultList();
    }

    @Override
    public int currentUnitsAmountStudent(Long term, Student student) {
        String jpql = "SELECT sum(c.course.units) FROM CourseStudent c WHERE c.student = :student AND c.course.term = :currentTerm";
        TypedQuery<Long> typedQuery = entityManager.createQuery(jpql,Long.class);
        typedQuery.setParameter("student",student);
        typedQuery.setParameter("currentTerm",term);
        if (typedQuery.getSingleResult() == null)
            return 0;
        return typedQuery.getSingleResult().intValue();
    }

}
