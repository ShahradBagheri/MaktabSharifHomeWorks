package service;

import model.Course;
import model.CourseStudent;
import model.Professor;
import model.Student;

import java.util.List;

public interface CourseStudentService {
    CourseStudent create(CourseStudent courseStudent);

    CourseStudent update(CourseStudent courseStudent);

    void delete(CourseStudent courseStudent);

    CourseStudent findById(Long id);

    List<CourseStudent> findCourseByStudent(Student student);

    int currentUnits(Long term,Student student);

    CourseStudent studentChoosing(Student student,Course course);

    boolean isStudentHighAvg(Long currentTerm,Student student);

    boolean choseCourseThisTerm(Course course,Student student,Long term);

    boolean hasPassedCourse(Course course,Student student);

    boolean studentOwnsCourse(Student student,Long courseStudentId);

    boolean professorOwnsCourseStudent(Professor professor,Long courseStudentId);

    boolean validScore(float score);
}
