package secondQuestion.repository.impl;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import secondQuestion.connection.SessionFactorySingleton;
import secondQuestion.model.Student;
import secondQuestion.model.Teacher;
import secondQuestion.repository.TeacherRepository;

import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Teacher save(Teacher teacher) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(teacher);
        Teacher savedTeacher = session.get(Teacher.class,id);
        transaction.commit();
        return savedTeacher;
    }

    @Override
    public void update(Teacher teacher) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(teacher);
        transaction.commit();

    }

    @Override
    public void delete(Teacher teacher) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(teacher);
        transaction.commit();
    }

    @Override
    public Teacher loadById(Long id) {
        var session = sessionFactory.getCurrentSession();
        return session.get(Teacher.class,id);
    }

    @Override
    public List<Teacher> loadAll() {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Teacher";
        Query<Teacher> query = session.createQuery(hql,Teacher.class);
        List<Teacher> result = query.list();
        transaction.commit();
        return result;
    }

    @Override
    public boolean contains(Teacher teacher) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "SELECT COUNT(*) FROM Teacher t WHERE t.id = :teacherId AND t.firstname = :teacherFirstname AND t.lastname = :teacherLastname AND t.birthdate = :teacherBirthdate AND t.major = :teacherMajor AND t.teacherId = :teacherTeacherId AND t.teacherTier = :teacherTeacherTier AND t.salary = :teacherSalary";
        Query<Long> query = session.createQuery(hql,Long.class);
        query.setParameter("teacherFirstname",teacher.getFirstname());
        query.setParameter("teacherLastname",teacher.getLastname());
        query.setParameter("teacherId",teacher.getId());
        query.setParameter("teacherBirthdate",teacher.getBirthdate());
        query.setParameter("teacherMajor",teacher.getMajor());
        query.setParameter("teacherTeacherTier",teacher.getTeacherTier());
        query.setParameter("teacherTeacherId",teacher.getTeacherId());
        query.setParameter("teacherSalary",teacher.getSalary());

        Long result = query.getSingleResult();
        transaction.commit();
        return result != 0;
    }
}
