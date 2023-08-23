package service;

import model.Course;
import model.Professor;

import java.util.List;
import java.util.Set;

public interface CourseService {

    Course submit(String name, long term, int unit, Professor professor);

    Course findById(long id);

    void remove(Course course);

    void update(Course course);

    List<Course> findByTerm(long term);

    Set<Long> getAllTerms();

    List<Course> findByProfessorAndTerm(Professor professor,Long term);

    boolean existsByTermAndId(Long term, Long id);
}
