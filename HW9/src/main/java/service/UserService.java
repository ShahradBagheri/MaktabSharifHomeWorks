package service;

import base.service.BaseService;
import model.User;

import java.sql.SQLException;

public interface UserService extends BaseService<Integer, User> {
    boolean isExistsUsername(String username) throws Exception;

    boolean isExistsNatCode(String natCode) throws SQLException;

    boolean isExistsEmail(String phone) throws SQLException;

    boolean isValidEntryData(String regex,String data);

    User checkCredentialInfoForLogin(String username,String password) throws Exception;
}
