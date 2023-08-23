package service;

import model.Student;

public interface StudentService {

    Student signup(String username,String firstname,String lastname);

    void remove(Student student);

    Student findById(Long id);

    void update(Student student);

    Student findByUsername(String username);
}
