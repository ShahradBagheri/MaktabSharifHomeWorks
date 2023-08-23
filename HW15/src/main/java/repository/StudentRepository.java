package repository;

import model.Student;

public interface StudentRepository {
    Student create(Student student);

    Student update(Student student);

    void delete(Student student);

    Student findById(Long id);

    Student findByUsername(String username);
}
