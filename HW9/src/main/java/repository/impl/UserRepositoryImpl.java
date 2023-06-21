package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import model.User;
import repository.UserRepository;
import util.ApplicationContext;
import util.Constant;

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
        return "(firstname,lastname,username,password,national_code,email)";
    }

    @Override
    public String getUpdateQueryParams() {
        return "?,?,?,?,?,?";
    }

    @Override
    public String getCountOfQuestionMarkForParams() {
        return "? ,? ,? ,? ,? ,?";
    }

    @Override
    public User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7));
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getUsername());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setString(5, entity.getNatCode());
        preparedStatement.setString(6, entity.getEmail());
    }

    @Override
    public boolean isExistsUsername(String username) throws SQLException {
        String sql="SELECT username FROM "+getTableName()+" WHERE username = ?";
        try (PreparedStatement preparedStatement = ApplicationContext.CONNECTION.prepareStatement(sql)){
            preparedStatement.setString(1,username);
            ResultSet foundUsername = preparedStatement.executeQuery();
            if (foundUsername.next())
                throw new RuntimeException();
        }
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
