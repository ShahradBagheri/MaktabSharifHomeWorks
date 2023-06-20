package base.repository.impl;

import base.model.BaseEntity;
import base.repository.BaseRepository;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseRepositoryImpl <ID extends Serializable, TYPE extends BaseEntity<ID>> implements BaseRepository {
    @Override
    public void save(BaseEntity entity) throws SQLException {

    }

    @Override
    public void delete(Serializable serializable) throws SQLException {

    }

    @Override
    public BaseEntity findById(Serializable serializable) throws SQLException {
        return null;
    }

    @Override
    public void update(BaseEntity entity) throws SQLException {

    }

    @Override
    public List findAll() throws SQLException {
        return null;
    }
    public abstract String getTableName();

    public abstract String getColumnsName();

    public abstract String getUpdateQueryParams();
    public abstract String getCountOfQuestionMarkForParams();
    public abstract TYPE mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public abstract void fillParamForStatement(PreparedStatement preparedStatement, TYPE entity) throws SQLException;
}
