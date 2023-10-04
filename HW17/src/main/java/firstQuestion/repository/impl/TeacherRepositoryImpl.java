package firstQuestion.repository.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.connection.SessionFactorySingleton;
import firstQuestion.repository.TeacherRepository;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import firstQuestion.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Teacher save(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        entityManager.remove(teacher);
    }

    @Override
    public Teacher loadById(Long id) {
        return entityManager.find(Teacher.class,id);
    }

    @Override
    public List<Teacher> loadAll() {
        String hql = "FROM Teacher";
        TypedQuery<Teacher> query = entityManager.createQuery(hql, Teacher.class);
        return query.getResultList();
    }

    @Override
    public boolean contains(Teacher teacher) {
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
