package secondQuestion.service;

import secondQuestion.enums.TeacherTier;
import secondQuestion.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher save(Teacher teacher);

    void update(Teacher teacher);

    void delete(Teacher teacher);

    Teacher loadById(Long id);

    List<Teacher> loadAll();

    boolean contains(Teacher teacher);

    Teacher signUp(String firstname, String lastname, String teacherId, TeacherTier teacherTier);
}
