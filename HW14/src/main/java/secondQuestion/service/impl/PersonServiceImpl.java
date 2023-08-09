package secondQuestion.service.impl;

import secondQuestion.model.Person;
import secondQuestion.repository.impl.PersonRepositoryImpl;
import secondQuestion.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final PersonRepositoryImpl personRepository = new PersonRepositoryImpl();

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void update(Person person) {
personRepository.update(person);
    }

    @Override
    public void delete(Person person) {
personRepository.delete(person);
    }

    @Override
    public Person loadById(Long id) {
        return personRepository.loadById(id);
    }

    @Override
    public List<Person> loadAll() {
        return personRepository.loadAll();
    }

    @Override
    public boolean contains(Person person) {
        return personRepository.contains(person);
    }

    @Override
    public Person signUp(String firstname, String lastname) {
        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        return personRepository.save(person);
    }
}
