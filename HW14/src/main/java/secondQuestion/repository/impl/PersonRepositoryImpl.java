package secondQuestion.repository.impl;

import org.hibernate.*;
import org.hibernate.query.Query;
import secondQuestion.connection.SessionFactorySingleton;
import secondQuestion.model.Person;
import secondQuestion.repository.PersonRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);
        criteriaQuery.select(root);
        Query<Person> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public boolean contains(Person person) {
        return false;
    }
}
