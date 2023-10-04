package firstQuestion.service.impl;

import firstQuestion.connection.EntityManagerSingleton;
import firstQuestion.model.Person;
import firstQuestion.repository.impl.PersonRepositoryImpl;
import firstQuestion.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public Person save(Person person) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to save in {}", PersonServiceImpl.class.getSimpleName());

        try {

            entityTransaction.begin();

            person = personRepository.save(person);

            entityTransaction.commit();
            logger.info("saved successfully in {}", PersonServiceImpl.class.getSimpleName());
            return person;

        } catch (Exception e) {

            entityTransaction.rollback();
            logger.error("failed to save in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public void update(Person person) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to update in {}", PersonServiceImpl.class.getSimpleName());
        try {

            entityTransaction.begin();

            personRepository.update(person);

            entityTransaction.commit();
            logger.info("updated successfully in {}", PersonServiceImpl.class.getSimpleName());
        } catch (Exception e) {

            entityTransaction.rollback();
            logger.error("failed to update in {}", PersonServiceImpl.class.getSimpleName());
        }
    }

    @Override
    public void delete(Person person) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to delete in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            personRepository.delete(person);

            entityTransaction.commit();
            logger.info("deleted successfully in {}", PersonServiceImpl.class.getSimpleName());
        } catch (Exception e) {

            entityTransaction.rollback();
            logger.error("failed to delete in {}", PersonServiceImpl.class.getSimpleName());
        }
    }

    @Override
    public Person loadById(Long id) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to load by id in {}", PersonServiceImpl.class.getSimpleName());
        try {

            entityTransaction.begin();

            Person person = personRepository.loadById(id);

            entityTransaction.commit();
            logger.info("loaded by id successfully in {}", PersonServiceImpl.class.getSimpleName());

            return person;
        } catch (Exception e) {

            entityTransaction.rollback();
            logger.error("failed to load by id in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public List<Person> loadAll() {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to loadAll by id in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            List<Person> people = personRepository.loadAll();

            entityTransaction.commit();
            logger.info("loadedAll successfully in {}", PersonServiceImpl.class.getSimpleName());

            return people;
        } catch (Exception e) {
            entityTransaction.rollback();
            logger.error("failed to loadAll in {}", PersonServiceImpl.class.getSimpleName());
            return null;
        }
    }

    @Override
    public boolean contains(Person person) {

        EntityTransaction entityTransaction = entityManager.getTransaction();
        logger.info("attempting to check contains in {}", PersonServiceImpl.class.getSimpleName());
        try {
            entityTransaction.begin();

            boolean contains = personRepository.contains(person);

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
    public Person signUp(String firstname, String lastname) {
        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        logger.info("attempting to signUp in {}", PersonServiceImpl.class.getSimpleName());
        return personRepository.save(person);
    }
}
