package repository;

import base.repository.BaseRepository;
import model.User;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<Integer, User> {
    boolean isExistsUsername(String username) throws SQLException;

    boolean isExistsNatCode(String natCode) throws SQLException;

    boolean isExistsPhone(String phone) throws SQLException;

    User findUserByUsername(String username) throws SQLException;
}
