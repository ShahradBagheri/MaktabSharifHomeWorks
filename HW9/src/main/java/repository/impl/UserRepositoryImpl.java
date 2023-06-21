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
        return "firstname = ?, lastname = ? , username = ?, password = ? , national_code = ? , email = ?";
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
    public int numberOfQuestionMarks() {
        return 6;
    }

    @Override
    public boolean isExistsUsername(String username) throws SQLException {
        String sql="SELECT username FROM "+getTableName()+" WHERE username = ?";
        try (PreparedStatement preparedStatement = ApplicationContext.CONNECTION.prepareStatement(sql)){
            preparedStatement.setString(1,username);
            ResultSet foundUsername = preparedStatement.executeQuery();
            if (foundUsername.next())
                throw new RuntimeException("this username already exits");
        }
        return false;
    }

    @Override
    public boolean isExistsNatCode(String natCode) throws SQLException {
        String sql="SELECT national_code FROM "+getTableName()+" WHERE national_code = ?";
        try (PreparedStatement preparedStatement = ApplicationContext.CONNECTION.prepareStatement(sql)){
            preparedStatement.setString(1,natCode);
            ResultSet foundUsername = preparedStatement.executeQuery();
            if (foundUsername.next())
                throw new RuntimeException("this national code already exits");
        }
        return false;
    }

    @Override
    public boolean isExistsEmail(String email) throws SQLException {
        String sql="SELECT email FROM "+getTableName()+" WHERE email = ?";
        try (PreparedStatement preparedStatement = ApplicationContext.CONNECTION.prepareStatement(sql)){
            preparedStatement.setString(1,email);
            ResultSet foundUsername = preparedStatement.executeQuery();
            if (foundUsername.next())
                throw new RuntimeException("this email code already exits");
        }
        return false;
    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE username = ?";
        try (PreparedStatement preparedStatement = ApplicationContext.CONNECTION.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet foundUser = preparedStatement.executeQuery();
            if (foundUser.next())
                return new User(
                        foundUser.getInt(1),
                        foundUser.getString(2),
                        foundUser.getString(3),
                        foundUser.getString(4),
                        foundUser.getString(5),
                        foundUser.getString(6),
                        foundUser.getString(7)
                );
        }
        return null;
    }
}
