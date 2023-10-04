package firstQuestion.repository;

import firstQuestion.model.Student;

import java.util.List;

public interface StudentRepository {
    Student save(Student student);

    void update(Student student);

    void delete(Student student);

    Student loadById(Long id);

    List<Student> loadAll();

    boolean contains(Student student);
}
