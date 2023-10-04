package firstQuestion.service.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.connection.SessionFactorySingleton;
import firstQuestion.model.Student;
import firstQuestion.model.enums.TeacherTier;
import firstQuestion.repository.impl.TeacherRepositoryImpl;
import firstQuestion.service.TeacherService;
import firstQuestion.model.Teacher;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl();
    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Teacher save(Teacher teacher) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            teacher = teacherRepository.save(teacher);

            entityTransaction.commit();
            return teacher;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public void update(Teacher teacher) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            teacherRepository.update(teacher);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public void delete(Teacher teacher) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            teacherRepository.delete(teacher);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public Teacher loadById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Teacher teacher = teacherRepository.loadById(id);

            entityTransaction.commit();

            return teacher;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public List<Teacher> loadAll() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            List<Teacher> teachers = teacherRepository.loadAll();

            entityTransaction.commit();

            return teachers;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public boolean contains(Teacher teacher) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            boolean contains = teacherRepository.contains(teacher);

            entityTransaction.commit();

            return contains;
        } catch (Exception e) {
            entityTransaction.rollback();
            return false;
        }
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
