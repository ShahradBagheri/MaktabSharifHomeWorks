package secondQuestion.repository;

import secondQuestion.model.Person;

import java.util.List;

public interface PersonRepository {
    void save(Person person);

    void update(Person person);

    void delete(Person person);

    List<Person> loadAll();

    boolean contains(Person person);
}
