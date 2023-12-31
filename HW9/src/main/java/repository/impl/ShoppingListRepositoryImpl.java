package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import model.ShoppingList;
import repository.ShoppingListRepository;
import util.ApplicationContext;

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
        return "(name,amount,price)";
    }

    @Override
    public String getUpdateQueryParams() {
        return "name = ?, amount = ?, price = ?";
    }

    @Override
    public String getCountOfQuestionMarkForParams() {
        return "? , ?, ?";
    }

    @Override
    public ShoppingList mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new ShoppingList(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4));
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, ShoppingList entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getAmount());
        preparedStatement.setInt(3, entity.getPrice());
    }

    @Override
    public int numberOfQuestionMarks() {
        return 3;
    }

    @Override
    public int sumAllPrices() throws SQLException {
        String query="SELECT sum(price) FROM "+getTableName();
        try (PreparedStatement statement = ApplicationContext.CONNECTION.prepareStatement(query)){
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        }
        return -1;
    }
}
