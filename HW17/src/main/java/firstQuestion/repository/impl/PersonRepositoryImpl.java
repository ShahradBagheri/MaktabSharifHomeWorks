package firstQuestion.repository.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.model.Person;
import firstQuestion.repository.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Person save(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    public void update(Person person) {
        entityManager.merge(person);
    }

    @Override
    public void delete(Person person) {
        entityManager.remove(person);
    }

    @Override
    public Person loadById(Long id) {
        return entityManager.find(Person.class,id);
    }

    @Override
    public List<Person> loadAll() {
        String hql = "FROM Person";
        TypedQuery<Person> query = entityManager.createQuery(hql,Person.class);
        return query.getResultList();
    }

    @Override
    public boolean contains(Person person) {
        String hql = "SELECT COUNT(*) FROM Person p WHERE p.id = :personId AND p.firstname = :personFirstname AND p.lastname = :personLastname AND p.birthdate = :personBirthdate";
        TypedQuery<Long> query = entityManager.createQuery(hql,Long.class);
        query.setParameter("personFirstname",person.getFirstname());
        query.setParameter("personLastname",person.getLastname());
        query.setParameter("personId",person.getId());
        query.setParameter("personBirthdate",person.getBirthdate());

        Long result = query.getSingleResult();
        return result != 0;
    }
}
