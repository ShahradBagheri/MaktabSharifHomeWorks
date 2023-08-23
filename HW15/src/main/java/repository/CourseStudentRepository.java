package repository;

import model.CourseStudent;
import model.Student;

import java.util.List;

public interface CourseStudentRepository {

    CourseStudent create(CourseStudent courseStudent);

    CourseStudent update(CourseStudent courseStudent);

    void delete(CourseStudent courseStudent);

    List<CourseStudent> findCourseByStudent(Student student);

    CourseStudent findById(Long id);
}
