package util;

import config.DatabaseConfig;
import repository.ShoppingListRepository;
import repository.UserRepository;
import repository.impl.ShoppingListRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.ShoppingListService;
import service.UserService;
import service.impl.ShoppingListServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.Connection;

public class ApplicationContext {
    public static final Connection CONNECTION;
    public  static final UserRepository USER_REPOSITORY;
    public static final ShoppingListRepository SHOPPING_LIST_REPOSITORY;
    public static final UserService USER_SERVICE;
    public static final ShoppingListService SHOPPING_LIST_SERVICE;

    static {
        CONNECTION = new DatabaseConfig().getCreatedConnection();
        USER_REPOSITORY = new UserRepositoryImpl();
        SHOPPING_LIST_REPOSITORY = new ShoppingListRepositoryImpl();
        USER_SERVICE = new UserServiceImpl(USER_REPOSITORY);
        SHOPPING_LIST_SERVICE = new ShoppingListServiceImpl(SHOPPING_LIST_REPOSITORY);
    }
}
