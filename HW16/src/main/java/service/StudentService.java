package service;

import model.Student;

public interface StudentService {

    Student signUp(Student student);

    Student update(Student student);

    Student findById(Long id);

    String generatePassword();

    Student login(String governmentId, String password);

    boolean socialIdValidation(String socialId);

    boolean governmentIdValidation(String governmentId);

    boolean studentIdValidation(String studentId);

    boolean entranceYearValidation(int entranceYear);

    boolean socialIdExists(String socialId);

    boolean governmentIdExists(String governmentId);

    boolean studentIdExists(String studentId);

    boolean hasGraduated(Student student);
}
