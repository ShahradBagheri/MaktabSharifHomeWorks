package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import model.User;
import repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl extends BaseRepositoryImpl<Integer, User> implements UserRepository {
    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public String getColumnsName() {
        return null;
    }

    @Override
    public String getUpdateQueryParams() {
        return null;
    }

    @Override
    public String getCountOfQuestionMarkForParams() {
        return null;
    }

    @Override
    public User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, User entity) throws SQLException {

    }

    @Override
    public boolean isExistsUsername(String username) throws SQLException {
        return false;
    }

    @Override
    public boolean isExistsNatCode(String natCode) throws SQLException {
        return false;
    }

    @Override
    public boolean isExistsEmail(String phone) throws SQLException {
        return false;
    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        return null;
    }
}
