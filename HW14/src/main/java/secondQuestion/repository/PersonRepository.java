package secondQuestion.repository;

import secondQuestion.model.Person;

import java.util.List;

public interface PersonRepository {
    Person save(Person person);

    void update(Person person);

    void delete(Person person);

    Person loadById(Long id);

    List<Person> loadAll();

    boolean contains(Person person);
}
