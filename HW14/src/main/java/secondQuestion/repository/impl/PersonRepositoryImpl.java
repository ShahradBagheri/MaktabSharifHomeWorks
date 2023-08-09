package secondQuestion.repository.impl;

import org.hibernate.*;
import org.hibernate.query.Query;
import secondQuestion.connection.SessionFactorySingleton;
import secondQuestion.model.Person;
import secondQuestion.repository.PersonRepository;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Person person) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(person);
        transaction.commit();
    }

    @Override
    public void update(Person person) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(person);
        transaction.commit();
    }

    @Override
    public void delete(Person person) {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(person);
        transaction.commit();
    }

    @Override
    public Person loadById(Long id) {
        var session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Override
    public List<Person> loadAll() {
        var session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Person";
        Query<Person> query = session.createQuery(hql,Person.class);
        return query.list();
    }

    @Override
    public boolean contains(Person person) {
        var session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(*) FROM Person p WHERE p.id = :personId AND p.firstname = :personFirstname AND p.lastname = :personLastname AND p.birthdate = :personBirthdate";
        Query<Long> query = session.createQuery(hql,Long.class);
        query.setParameter("personFirstname",person.getFirstname());
        query.setParameter("personLastname",person.getLastname());
        query.setParameter("personId",person.getId());
        query.setParameter("personBirthdate",person.getBirthdate());

        Long result = query.getSingleResult();
        return result != 0;
    }
}
