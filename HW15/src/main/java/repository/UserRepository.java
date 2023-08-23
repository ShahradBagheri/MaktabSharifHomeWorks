package repository;

import model.User;


public interface UserRepository {

    User findByUsername(String username);
}
