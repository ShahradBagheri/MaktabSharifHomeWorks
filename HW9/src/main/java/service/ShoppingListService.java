package service;

import base.service.BaseService;
import model.ShoppingList;

import java.sql.SQLException;

public interface ShoppingListService extends BaseService<Integer, ShoppingList> {
    int sumAllPrices() throws SQLException;
}
