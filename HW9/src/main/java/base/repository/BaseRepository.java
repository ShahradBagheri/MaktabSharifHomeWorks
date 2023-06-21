package base.repository;

import base.model.BaseEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface BaseRepository <ID extends Serializable, TYPE extends BaseEntity<ID>>{
    void save(TYPE entity) throws SQLException;

    void delete(ID id) throws SQLException;
    TYPE findById(ID id) throws SQLException;
    int update(TYPE entity) throws SQLException;
    List<TYPE> findAll() throws SQLException;

}
