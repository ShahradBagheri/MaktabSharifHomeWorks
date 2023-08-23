package repository;

import model.Student;

import java.util.List;

public interface StudentRepository {
    Student create(Student student);

    Student update(Student student);

    void delete(Student student);

    Student findById(Long id);

    List<Student> findAll();

    Student findByUsername(String username);
}
