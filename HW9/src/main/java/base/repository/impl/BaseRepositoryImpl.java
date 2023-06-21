package base.repository.impl;

import base.model.BaseEntity;
import base.repository.BaseRepository;
import util.ApplicationContext;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepositoryImpl <ID extends Serializable, TYPE extends BaseEntity<ID>> implements BaseRepository<ID, TYPE> {
    @Override
    public void save(TYPE entity) throws SQLException {
        String query = "INSERT INTO " + getTableName() +" "+getColumnsName()+ " VALUES (" + getCountOfQuestionMarkForParams() + ")";
        try (PreparedStatement statement = ApplicationContext.CONNECTION.prepareStatement(query)) {
            fillParamForStatement(statement, entity);
            statement.execute();
        }

    }

    @Override
    public void delete(ID id) throws SQLException {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ? ";
        try (PreparedStatement statement = ApplicationContext.CONNECTION.prepareStatement(query)) {
            statement.setInt(1, (Integer) id);
            statement.execute();
        }
    }

    @Override
    public TYPE findById(ID id) throws SQLException {
        String query="SELECT * FROM "+getTableName()+ " WHERE id = ? ;";
        try (PreparedStatement statement = ApplicationContext.CONNECTION.prepareStatement(query)){
            statement.setInt(1, (Integer) id);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next())
                return mapResultSetToEntity(resultSet);
        }
        return null;
    }

    @Override
    public void update(TYPE entity) throws SQLException {
        String query = "UPDATE " + getTableName() + " SET " + getUpdateQueryParams() + " WHERE id = ?";
        try (PreparedStatement statement = ApplicationContext.CONNECTION.prepareStatement(query)) {
            fillParamForStatement(statement, entity);
            statement.executeUpdate();
        }
    }

    @Override
    public List<TYPE> findAll() throws SQLException {
        String query = " SELECT * FROM " + getTableName();
        try (PreparedStatement statement = ApplicationContext.CONNECTION.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<TYPE> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;
        }
    }
    public abstract String getTableName();

    public abstract String getColumnsName();

    public abstract String getUpdateQueryParams();
    public abstract String getCountOfQuestionMarkForParams();
    public abstract TYPE mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public abstract void fillParamForStatement(PreparedStatement preparedStatement, TYPE entity) throws SQLException;
}
