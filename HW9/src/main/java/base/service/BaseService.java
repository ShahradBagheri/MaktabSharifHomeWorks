package base.service;

import java.sql.SQLException;
import java.util.List;

public interface BaseService <ID,TYPE>{
    void save(TYPE entity) throws SQLException;

    int delete(ID id) throws SQLException;

    List<TYPE> findAll() throws SQLException;

    TYPE findById(ID id) throws SQLException;

    int update(TYPE entity) throws SQLException;
}
