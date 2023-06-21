package service.impl;

import base.service.impl.BaseServiceImpl;
import model.ShoppingList;
import repository.ShoppingListRepository;
import service.ShoppingListService;

import java.sql.SQLException;

public class ShoppingListServiceImpl extends BaseServiceImpl<Integer, ShoppingList, ShoppingListRepository> implements ShoppingListService {
    public ShoppingListServiceImpl(ShoppingListRepository repository) {
        super(repository);
    }

    @Override
    public int sumAllPrices() throws SQLException {
        return repository.sumAllPrices();
    }
}
