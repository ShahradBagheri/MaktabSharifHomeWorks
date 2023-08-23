package service.impl;

import model.Course;
import model.CourseStudent;
import model.Professor;
import model.Student;
import repository.CourseStudentRepository;
import service.CourseStudentService;
import util.ApplicationContext;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Objects;

public class CourseStudentServiceImpl implements CourseStudentService {

    EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    CourseStudentRepository courseStudentRepository = ApplicationContext.courseStudentRepository;

    @Override
    public CourseStudent create(CourseStudent courseStudent) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            courseStudentRepository.create(courseStudent);
            transaction.commit();
            return courseStudent;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public CourseStudent update(CourseStudent courseStudent) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            courseStudentRepository.update(courseStudent);
            transaction.commit();
            return courseStudent;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(CourseStudent courseStudent) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            courseStudentRepository.delete(courseStudent);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public CourseStudent findById(Long id) {

        EntityTransaction transaction = entityManager.getTransaction();
        CourseStudent courseStudent;

        try {
            transaction.begin();
            courseStudent = courseStudentRepository.findById(id);
            transaction.commit();
            return courseStudent;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<CourseStudent> findCourseByStudent(Student student) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            List<CourseStudent> courseStudents = courseStudentRepository.findCourseByStudent(student);
            transaction.commit();
            return courseStudents;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isStudentHighAvg(Long currentTerm, Student student) {

        EntityTransaction transaction = entityManager.getTransaction();
        List<CourseStudent> courseStudents;

        try {
            transaction.begin();
            courseStudents = courseStudentRepository.findCourseStudentByTermAndStudent(currentTerm - 1, student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return false;
        }

//        if (courseStudents == null || courseStudents.contains(null))
//            return false;

        double avg = courseStudents.stream()
                .map(CourseStudent::getScore)
                .mapToDouble(Float::doubleValue)
                .average()
                .orElse(0.0);

        return avg >= 18;

    }

    @Override
    public int currentUnits(Long term, Student student) {

        EntityTransaction transaction = entityManager.getTransaction();
        int units;

        try {
            transaction.begin();
            units = courseStudentRepository.currentUnitsAmountStudent(term,student);
            transaction.commit();
            return units;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public CourseStudent studentChoosing(Student student, Course course) {

        EntityTransaction transaction = entityManager.getTransaction();

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setStudent(student);

        try {
            transaction.begin();
            courseStudentRepository.create(courseStudent);
            transaction.commit();
            return courseStudent;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean choseCourseThisTerm(Course course, Student student, Long term) {

        EntityTransaction transaction = entityManager.getTransaction();
        List<CourseStudent> courseStudents;

        try {
            transaction.begin();
            courseStudents = courseStudentRepository.findCourseStudentByTermAndStudent(term,student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return false;
        }

        List<String> courses = courseStudents.stream()
                .map(courseStudent -> courseStudent.getCourse().getName())
                .toList();

        return courses.contains(course.getName());
    }

    @Override
    public boolean hasPassedCourse(Course course, Student student) {

        EntityTransaction transaction = entityManager.getTransaction();
        List<CourseStudent> courseStudents;

        try {
            transaction.begin();
            courseStudents = courseStudentRepository.findCourseByStudent(student);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return false;
        }

        if (courseStudents.stream()
                .filter(courseStudent -> Objects.equals(courseStudent.getCourse().getName(), course.getName()))
                .map(CourseStudent::getScore)
                .toList()
                .contains(null))
            return false;

        List<String> courses = courseStudents.stream()
                .filter(courseStudent -> courseStudent.getScore() >= 10)
                .map(courseStudent -> courseStudent.getCourse().getName())
                .toList();

        return courses.contains(course.getName());
    }

    @Override
    public boolean studentOwnsCourse(Student student, Long courseStudentId) {
        EntityTransaction transaction = entityManager.getTransaction();
        CourseStudent courseStudent;

        try {
            transaction.begin();
            courseStudent = courseStudentRepository.findById(courseStudentId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return false;
        }

        return courseStudent.getStudent().equals(student);
    }

    @Override
    public boolean professorOwnsCourseStudent(Professor professor, Long courseStudentId) {
        EntityTransaction transaction = entityManager.getTransaction();
        CourseStudent courseStudent;

        try {
            transaction.begin();
            courseStudent = courseStudentRepository.findById(courseStudentId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return false;
        }
        return courseStudent.getCourse().getProfessor().equals(professor);
    }

    @Override
    public boolean validScore(float score) {
        return score >= 0 && score <= 20;
    }
}
