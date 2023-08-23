package repository;

import model.Course;
import model.Professor;

import java.util.List;

public interface CourseRepository {
    Course create(Course course);

    Course update(Course course);

    void delete(Course course);

    Course findById(Long id);

    List<Course> findAll();

    List<Course> findByTerm(Long term);

    List<Course> findByProfessorAndTerm(Professor professor,Long term);
}
