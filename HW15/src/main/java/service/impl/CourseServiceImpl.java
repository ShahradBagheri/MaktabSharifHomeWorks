package service.impl;

import model.Course;
import model.Professor;
import repository.CourseRepository;
import service.CourseService;
import util.ApplicationContext;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {


    CourseRepository courseRepository = ApplicationContext.courseRepository;
    EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Course submit(String name, long term, int unit, Professor professor) {

        EntityTransaction entityTransaction = entityManager.getTransaction();

        Course course = new Course();
        course.setName(name);
        course.setTerm(term);
        course.setUnits(unit);
        course.setProfessor(professor);

        try {
            entityTransaction.begin();

            course = courseRepository.create(course);

            entityTransaction.commit();
            return course;
        } catch (Exception e) {
            entityTransaction.rollback();
        }
        return null;
    }

    @Override
    public Course findById(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course course = courseRepository.findById(id);
            transaction.commit();
            return course;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(Course course) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            courseRepository.delete(course);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public void update(Course course) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            courseRepository.update(course);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public List<Course> findByTerm(long term) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            List<Course> courses = courseRepository.findByTerm(term);

            entityTransaction.commit();
            return  courses;
        } catch (Exception e) {
            entityTransaction.rollback();
        }
        return null;
    }

    @Override
    public Set<Long> getAllTerms() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        List<Course> courses;
        try {
            entityTransaction.begin();
            
            courses = courseRepository.findAll();
            
            entityTransaction.commit();
            
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }

        return courses.stream().map(Course::getTerm).collect(Collectors.toSet());
    }

    @Override
    public List<Course> findByProfessorAndTerm(Professor professor,Long term) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        List<Course> courses;
        try {
            entityTransaction.begin();

            courses = courseRepository.findByProfessorAndTerm(professor,term);

            entityTransaction.commit();
            return courses;

        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public boolean existsByTermAndId(Long term, Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course course = courseRepository.findById(id);
            transaction.commit();
            if (course == null)
                return false;
            return Objects.equals(course.getTerm(), term);
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return false;
    }
}
