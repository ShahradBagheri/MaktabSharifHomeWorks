package repository.impl;

import model.User;
import repository.UserRepository;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager = EntityManagerSingleton.getInstanceEM();

    @Override
    public User findByUsername(String username) {
        String hql = "SELECT u FROM User u WHERE u.username = :username";
        TypedQuery<User> typedQuery = entityManager.createQuery(hql, User.class);
        typedQuery.setParameter("username",username);
        return typedQuery.getSingleResult();
    }
}
