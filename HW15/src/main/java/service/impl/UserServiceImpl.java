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

    public User singUp(String username, String password, Role role){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            userRepository.create(user);
            entityTransaction.commit();
            return user;
        } catch (Exception e){
            entityTransaction.rollback();
        }
        return null;
    }

    public void remove(User user){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            userRepository.delete(user);
            entityTransaction.commit();
        } catch (Exception e){
            entityTransaction.rollback();
        }
    }

    @Override
    public User findByUsername(String username) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            User user = userRepository.findByUsername(username);
            entityTransaction.commit();
            return user;
        } catch (Exception e){
            entityTransaction.rollback();
        }
        return null;
    }
}
