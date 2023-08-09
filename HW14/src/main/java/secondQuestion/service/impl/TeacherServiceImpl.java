package secondQuestion.service.impl;

import secondQuestion.enums.TeacherTier;
import secondQuestion.model.Teacher;
import secondQuestion.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private TeacherServiceImpl teacherService = new TeacherServiceImpl();

    @Override
    public Teacher save(Teacher teacher) {
        return teacherService.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherService.update(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherService.delete(teacher);
    }

    @Override
    public Teacher loadById(Long id) {
        return teacherService.loadById(id);
    }

    @Override
    public List<Teacher> loadAll() {
        return teacherService.loadAll();
    }

    @Override
    public boolean contains(Teacher teacher) {
        return teacherService.contains(teacher);
    }

    @Override
    public Teacher signUp(String firstname, String lastname, String teacherId, TeacherTier teacherTier) {
        Teacher teacher = new Teacher();
        teacher.setFirstname(firstname);
        teacher.setLastname(lastname);
        teacher.setTeacherId(teacherId);
        teacher.setTeacherTier(teacherTier);

        return teacherService.save(teacher);
    }
}
