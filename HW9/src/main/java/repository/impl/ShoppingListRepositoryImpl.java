package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import model.ShoppingList;
import repository.ShoppingListRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingListRepositoryImpl extends BaseRepositoryImpl<Integer, ShoppingList> implements ShoppingListRepository {
    @Override
    public String getTableName() {
        return "shopping_list";
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
    public ShoppingList mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, ShoppingList entity) throws SQLException {

    }
}
