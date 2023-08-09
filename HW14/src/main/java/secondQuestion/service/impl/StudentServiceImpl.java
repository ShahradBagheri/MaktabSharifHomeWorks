package secondQuestion.service.impl;

import secondQuestion.model.Student;
import secondQuestion.repository.impl.StudentRepositoryImpl;
import secondQuestion.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student loadById(Long id) {
        return studentRepository.loadById(id);
    }

    @Override
    public List<Student> loadAll() {
        return studentRepository.loadAll();
    }

    @Override
    public boolean contains(Student student) {
        return studentRepository.contains(student);
    }

    @Override
    public Student signUp(String firstname, String lastname, String studentId, String admissionYear) {
        Student student = new Student();
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setStudentId(studentId);
        student.setAdmissionYear(admissionYear);
        return studentRepository.save(student);
    }
}
