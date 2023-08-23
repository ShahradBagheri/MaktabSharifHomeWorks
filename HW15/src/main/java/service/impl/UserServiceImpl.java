package service.impl;

import enumeration.Role;
import model.User;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;
import util.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    EntityManager entityManager = EntityManagerSingleton.getInstanceEM();
    UserRepository userRepository = ApplicationContext.userRepository;

    @Override
    public User signIn(String username, String password) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            User user = userRepository.findByUsername(username);
            if (Objects.equals(user.getPassword(), password)) {
                entityTransaction.commit();
                return user;
            }
        } catch (Exception e) {
            entityTransaction.rollback();
        }
        return null;
    }
}
