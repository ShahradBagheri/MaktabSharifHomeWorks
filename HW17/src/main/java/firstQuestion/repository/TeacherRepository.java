package firstQuestion.repository;

import firstQuestion.model.Teacher;

import java.util.List;

public interface TeacherRepository {
    Teacher save(Teacher teacher);

    void update(Teacher teacher);

    void delete(Teacher teacher);

    Teacher loadById(Long id);

    List<Teacher> loadAll();

    boolean contains(Teacher teacher);
}
