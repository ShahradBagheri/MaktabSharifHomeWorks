package repository;

import base.repository.BaseRepository;
import model.User;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<Integer, User> {
    boolean isExistsUsername(String username) throws SQLException;

    boolean isExistsNatCode(String natCode) throws SQLException;

    boolean isExistsEmail(String phone) throws SQLException;

    User findUserByUsername(String username) throws SQLException;
    int sumAllPrices() throws SQLException;
}
