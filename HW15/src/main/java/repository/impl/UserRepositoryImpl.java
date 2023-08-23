package repository.impl;

import model.User;
import repository.UserRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public List<User> findAll() {
        String hql = "SELECT u FROM User u";
        TypedQuery<User> typedQuery = entityManager.createQuery(hql, User.class);
        return typedQuery.getResultList();
    }

    @Override
    public User findByUsername(String username) {
        String hql = "SELECT u FROM User u WHERE u.username = :username";
        TypedQuery<User> typedQuery = entityManager.createQuery(hql, User.class);
        typedQuery.setParameter("username",username);
        return typedQuery.getSingleResult();
    }
}
