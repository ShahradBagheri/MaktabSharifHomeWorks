package firstQuestion.service.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.model.Person;
import firstQuestion.repository.impl.PersonRepositoryImpl;
import firstQuestion.service.PersonService;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public Person save(Person person) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            person = personRepository.save(person);

            entityTransaction.commit();
            return person;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public void update(Person person) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            personRepository.update(person);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public void delete(Person person) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            personRepository.delete(person);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }

    @Override
    public Person loadById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            Person person = personRepository.loadById(id);

            entityTransaction.commit();

            return person;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public List<Person> loadAll() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            List<Person> people = personRepository.loadAll();

            entityTransaction.commit();

            return people;
        } catch (Exception e) {
            entityTransaction.rollback();
            return null;
        }
    }

    @Override
    public boolean contains(Person person) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            boolean contains = personRepository.contains(person);

            entityTransaction.commit();

            return contains;
        } catch (Exception e) {
            entityTransaction.rollback();
            return false;
        }
    }

    @Override
    public Person signUp(String firstname, String lastname) {
        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        return personRepository.save(person);
    }
}
