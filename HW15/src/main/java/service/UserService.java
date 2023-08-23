package service;

import enumeration.Role;
import model.User;

public interface UserService {

    User signIn(String username, String password);

    User singUp(String username, String password, Role role);

    void remove(User user);

    User findByUsername(String username);
}
