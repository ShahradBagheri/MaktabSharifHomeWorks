package firstQuestion.service.impl;

import firstQuestion.enums.TeacherTier;
import firstQuestion.repository.impl.TeacherRepositoryImpl;
import firstQuestion.service.TeacherService;
import firstQuestion.model.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl();

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherRepository.update(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher loadById(Long id) {
        return teacherRepository.loadById(id);
    }

    @Override
    public List<Teacher> loadAll() {
        return teacherRepository.loadAll();
    }

    @Override
    public boolean contains(Teacher teacher) {
        return teacherRepository.contains(teacher);
    }

    @Override
    public Teacher signUp(String firstname, String lastname, String teacherId, TeacherTier teacherTier) {
        Teacher teacher = new Teacher();
        teacher.setFirstname(firstname);
        teacher.setLastname(lastname);
        teacher.setTeacherId(teacherId);
        teacher.setTeacherTier(teacherTier);

        return teacherRepository.save(teacher);
    }
}
