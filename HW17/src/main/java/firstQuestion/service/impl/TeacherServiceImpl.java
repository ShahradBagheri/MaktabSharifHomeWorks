package firstQuestion.service.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.model.enums.TeacherTier;
import firstQuestion.repository.impl.TeacherRepositoryImpl;
import firstQuestion.service.TeacherService;
import firstQuestion.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryImpl teacherRepository = new TeacherRepositoryImpl();
    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public Teacher save(Teacher teacher) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to save in {}", PersonServiceImpl.class.getSimpleName());

        try {
            entityTransaction.begin();

            teacher = teacherRepository.save(teacher);

            entityTransaction.commit();
            logger.info("saved successfully in {}", PersonServiceImpl.class.getSimpleName());
            return teacher;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to save in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public void update(Teacher teacher) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to update in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            teacherRepository.update(teacher);

            entityTransaction.commit();
            logger.info("updated successfully in {}", PersonServiceImpl.class.getSimpleName());
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to update in {}", PersonServiceImpl.class.getSimpleName());
        }
    }

    @Override
    public void delete(Teacher teacher) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to delete in {}", PersonServiceImpl.class.getSimpleName());

        try {
            entityTransaction.begin();

            teacherRepository.delete(teacher);

            entityTransaction.commit();
            logger.info("deleted successfully in {}", PersonServiceImpl.class.getSimpleName());
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to delete in {}", PersonServiceImpl.class.getSimpleName());
        }
    }

    @Override
    public Teacher loadById(Long id) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to load by id in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            Teacher teacher = teacherRepository.loadById(id);

            entityTransaction.commit();
            logger.info("loaded by id successfully in {}", PersonServiceImpl.class.getSimpleName());

            return teacher;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to load by id in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public List<Teacher> loadAll() {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to loadAll by id in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            List<Teacher> teachers = teacherRepository.loadAll();

            entityTransaction.commit();
            logger.info("loadedAll successfully in {}", PersonServiceImpl.class.getSimpleName());

            return teachers;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to loadAll in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public boolean contains(Teacher teacher) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to check contains in {}", PersonServiceImpl.class.getSimpleName());

        try {
            entityTransaction.begin();

            boolean contains = teacherRepository.contains(teacher);

            entityTransaction.commit();
            logger.info("checked contains successfully in {}", PersonServiceImpl.class.getSimpleName());

            return contains;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to check contains in {}", PersonServiceImpl.class.getSimpleName());
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

        logger.info("attempting to signUp in {}", PersonServiceImpl.class.getSimpleName());
        return teacherRepository.save(teacher);
    }
}
