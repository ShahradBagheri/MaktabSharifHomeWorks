package firstQuestion.repository.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.repository.TeacherRepository;
import firstQuestion.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    @Override
    public Teacher save(Teacher teacher) {

        logger.info("attempting to save in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.persist(teacher);
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {

        logger.info("attempting to save in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.merge(teacher);
    }

    @Override
    public void delete(Teacher teacher) {

        logger.info("attempting to delete in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.remove(teacher);
    }

    @Override
    public Teacher loadById(Long id) {

        logger.info("attempting to load by id in {}", PersonRepositoryImpl.class.getSimpleName());
        return entityManager.find(Teacher.class,id);
    }

    @Override
    public List<Teacher> loadAll() {

        logger.info("attempting to loadAll in {}", PersonRepositoryImpl.class.getSimpleName());
        String hql = "FROM Teacher";
        TypedQuery<Teacher> query = entityManager.createQuery(hql, Teacher.class);
        return query.getResultList();
    }

    @Override
    public boolean contains(Teacher teacher) {

        logger.info("attempting to check contains in {}", PersonRepositoryImpl.class.getSimpleName());
        String hql = "SELECT COUNT(*) FROM Teacher t WHERE t.id = :teacherId AND t.firstname = :teacherFirstname AND t.lastname = :teacherLastname AND t.birthdate = :teacherBirthdate AND t.major = :teacherMajor AND t.teacherId = :teacherTeacherId AND t.teacherTier = :teacherTeacherTier AND t.salary = :teacherSalary";
        TypedQuery<Long> query = entityManager.createQuery(hql, Long.class);
        query.setParameter("teacherFirstname", teacher.getFirstname());
        query.setParameter("teacherLastname", teacher.getLastname());
        query.setParameter("teacherId", teacher.getId());
        query.setParameter("teacherBirthdate", teacher.getBirthdate());
        query.setParameter("teacherMajor", teacher.getMajor());
        query.setParameter("teacherTeacherTier", teacher.getTeacherTier());
        query.setParameter("teacherTeacherId", teacher.getTeacherId());
        query.setParameter("teacherSalary", teacher.getSalary());

        Long result = query.getSingleResult();
        return result != 0;
    }
}
