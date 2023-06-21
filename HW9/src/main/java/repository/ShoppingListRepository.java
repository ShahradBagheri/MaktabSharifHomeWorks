package repository;

import base.repository.BaseRepository;
import model.ShoppingList;

import java.sql.SQLException;

public interface ShoppingListRepository extends BaseRepository<Integer, ShoppingList> {
    int sumAllPrices() throws SQLException;
}
