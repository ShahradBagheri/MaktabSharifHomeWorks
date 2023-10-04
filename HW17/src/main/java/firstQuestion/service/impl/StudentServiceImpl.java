package firstQuestion.service.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.model.Student;
import firstQuestion.repository.impl.StudentRepositoryImpl;
import firstQuestion.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public Student save(Student student) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to save in {}", PersonServiceImpl.class.getSimpleName());

        try {
            entityTransaction.begin();

            student = studentRepository.save(student);


            entityTransaction.commit();
            logger.info("saved successfully in {}", PersonServiceImpl.class.getSimpleName());

            return student;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to save in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public void update(Student student) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to update in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            studentRepository.update(student);

            entityTransaction.commit();
            logger.info("updated successfully in {}", PersonServiceImpl.class.getSimpleName());
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to update in {}", PersonServiceImpl.class.getSimpleName());
        }
    }

    @Override
    public void delete(Student student) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to delete in {}", PersonServiceImpl.class.getSimpleName());

        try {
            entityTransaction.begin();

            studentRepository.delete(student);

            entityTransaction.commit();
            logger.info("deleted successfully in {}", PersonServiceImpl.class.getSimpleName());
        } catch (Exception e) {

            entityTransaction.rollback();
            logger.error("failed to delete in {}", PersonServiceImpl.class.getSimpleName());
        }
    }

    @Override
    public Student loadById(Long id) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to load by id in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            Student student = studentRepository.loadById(id);

            entityTransaction.commit();
            logger.info("loaded by id successfully in {}", PersonServiceImpl.class.getSimpleName());

            return student;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to load by id in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public List<Student> loadAll() {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to loadAll by id in {}", PersonServiceImpl.class.getSimpleName());

        try {
            entityTransaction.begin();

            List<Student> students = studentRepository.loadAll();

            entityTransaction.commit();
            logger.info("loadedAll successfully in {}", PersonServiceImpl.class.getSimpleName());

            return students;
        } catch (Exception e) {

            entityTransaction.rollback();
            logger.error("failed to loadAll in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public boolean contains(Student student) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to check contains in {}", PersonServiceImpl.class.getSimpleName());

        try {
            entityTransaction.begin();

            boolean contains = studentRepository.contains(student);

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
    public Student signUp(String firstname, String lastname, String studentId, String admissionYear) {
        Student student = new Student();
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setStudentId(studentId);
        student.setAdmissionYear(admissionYear);
        logger.info("attempting to signUp in {}", PersonServiceImpl.class.getSimpleName());
        return save(student);
    }
}
