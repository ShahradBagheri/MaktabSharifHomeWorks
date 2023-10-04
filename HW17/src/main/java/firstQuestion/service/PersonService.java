package firstQuestion.service;

import firstQuestion.model.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);

    void update(Person person);

    void delete(Person person);

    Person loadById(Long id);

    List<Person> loadAll();

    boolean contains(Person person);

    Person signUp(String firstname,String lastname);
}
