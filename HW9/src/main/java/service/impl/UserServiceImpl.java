package service.impl;

import base.repository.BaseRepository;
import base.service.impl.BaseServiceImpl;
import model.User;
import repository.UserRepository;
import service.UserService;
import util.Constant;

import java.sql.SQLException;

public class UserServiceImpl extends BaseServiceImpl<Integer,User,UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public boolean isExistsUsername(String username) throws Exception {
        return repository.isExistsUsername(username);
    }

    @Override
    public boolean isExistsNatCode(String natCode) throws SQLException {
        return repository.isExistsNatCode(natCode);
    }

    @Override
    public boolean isExistsEmail(String email) throws SQLException {
        return repository.isExistsEmail(email);
    }

    @Override
    public User checkCredentialInfoForLogin(String username, String password) throws Exception {
        User foundUser=repository.findUserByUsername(username);
        if (foundUser!=null){
            if (username.equals(foundUser.getUsername()) &&
                    password.equals(foundUser.getPassword()))
                return foundUser;
            else
                throw new BadCredentialException();
        }
        throw new UserNotFoundException();
    }
}
