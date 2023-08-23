package service;

import model.Student;

public interface StudentService {

    Student signup(Student student);

    void remove(Student student);

    Student findById(Long id);

    void update(Student student);

    Student findByUsername(String username);

    boolean existsById(Long id);
}
