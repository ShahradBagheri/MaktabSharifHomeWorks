package firstQuestion.service;

import firstQuestion.model.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student);

    void update(Student student);

    void delete(Student student);

    Student loadById(Long id);

    List<Student> loadAll();

    boolean contains(Student student);

    Student signUp(String firstname,String lastname, String studentId, String admissionYear);
}
