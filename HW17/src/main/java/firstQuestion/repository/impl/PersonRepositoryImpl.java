package firstQuestion.repository.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.model.Person;
import firstQuestion.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    @Override
    public Person save(Person person) {

        logger.info("attempting to save in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.persist(person);
        return person;
    }

    @Override
    public void update(Person person) {

        logger.info("attempting to update in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.merge(person);
    }

    @Override
    public void delete(Person person) {

        logger.info("attempting to delete in {}", PersonRepositoryImpl.class.getSimpleName());
        entityManager.remove(person);
    }

    @Override
    public Person loadById(Long id) {

        logger.info("attempting to find in {}", PersonRepositoryImpl.class.getSimpleName());
        return entityManager.find(Person.class,id);
    }

    @Override
    public List<Person> loadAll() {

        logger.info("attempting to loadAll in {}", PersonRepositoryImpl.class.getSimpleName());
        String hql = "FROM Person";
        TypedQuery<Person> query = entityManager.createQuery(hql,Person.class);
        return query.getResultList();
    }

    @Override
    public boolean contains(Person person) {

        logger.info("attempting to check contains in {}", PersonRepositoryImpl.class.getSimpleName());
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
