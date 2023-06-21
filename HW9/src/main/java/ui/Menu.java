package ui;

import model.*;
import util.ApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static void run() {
        while (true) {
            System.out.println("Choose an option \n1.Signup\n2.Login\n3.Exit");
            switch (scanner.nextLine()) {
                case "1":
                    signupMenu();
                    break;
                case "2":
                    loginMenu();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not a valid option");
            }
        }
    }

    public static void signupMenu() {
        System.out.println("Enter firstname");
        String firstname = scanner.nextLine();
        System.out.println("Enter lastname");
        String lastname = scanner.nextLine();
        String username = validateUsername();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        String natCode = validateNatCode();
        String email = validateEmail();
        try {
            ApplicationContext.USER_SERVICE.save(new User(firstname, lastname, username, password, natCode, email));
            System.out.println("You have signed up");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String validateUsername() {
        String username;
        while (true) {
            System.out.println("Enter username");
            username = scanner.nextLine();
            try {
                if (!ApplicationContext.USER_SERVICE.isExistsUsername(username))
                    break;
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        }
        return username;
    }

    public static String validateEmail() {
        String email;
        while (true) {
            System.out.println("Enter email");
            email = scanner.nextLine();
            try {
                if (!ApplicationContext.USER_SERVICE.isExistsEmail(email))
                    break;
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        }
        return email;
    }

    public static String validateNatCode() {
        String natCode;
        while (true) {
            System.out.println("Enter national code");
            natCode = scanner.nextLine();
            try {
                if (!ApplicationContext.USER_SERVICE.isExistsNatCode(natCode))
                    break;
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        }
        return natCode;
    }

    public static void loginMenu() {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        try {
            User loggedInUser = ApplicationContext.USER_SERVICE.checkCredentialInfoForLogin(username, password);
            dashboardMenu();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dashboardMenu() {
        while (true) {
            System.out.println("Choose an option\n1.Edit users\n2.Shop\n3.Logout");
            switch (scanner.nextLine()) {
                case "1":
                    editUsers();
                    break;
                case "2":
                    shop();
                    break;
                case "3":
                    run();
                    break;
                default:
                    System.out.println("Not a valid option");

            }
        }
    }

    public static void editUsers() {
        System.out.println("Choose an option\n1.Load user\n2.Delete user\n3.Update user\n4.Go back");
        switch (scanner.nextLine()) {
            case "1":
                System.out.println(loadUser());
                break;
            case "2":
                deleteUser();
                break;
            case "3":
                updateUser();
                break;
            default:
                System.out.println("Not a valid option");
        }
    }

    public static String loadUser() {
        User loadedUser;
        while (true) {
            System.out.println("Enter user id");
            try {
                int userId = Integer.parseInt(scanner.nextLine());
                loadedUser = ApplicationContext.USER_SERVICE.findById(userId);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a number");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return loadedUser.toString();
    }

    public static void deleteUser() {
        int idToDelete;
        while (true) {
            System.out.println("Enter user id");
            try {
                idToDelete = Integer.parseInt(scanner.nextLine());
                ApplicationContext.USER_REPOSITORY.delete(idToDelete);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a number");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                ;
            }
        }
    }

    public static void updateUser() {
        int rowsUpdated;
        while (true) {
            System.out.println("Enter id");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter firstname");
            String firstname = scanner.nextLine();
            System.out.println("Enter lastname");
            String lastname = scanner.nextLine();
            String username = validateUsername();
            System.out.println("Enter Password");
            String password = scanner.nextLine();
            String natCode = validateNatCode();
            String email = validateEmail();
            try {
                rowsUpdated = ApplicationContext.USER_SERVICE.update(new User(id, firstname, lastname, username, password, natCode, email));
                if (rowsUpdated != 0) {
                    System.out.println("User Updated");
                    break;
                } else {
                    System.out.println("No user with that id was found or any user was updated");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void shop() {
        while (true) {
            System.out.println("Choose an option\n1.Add to shopping list\n2.Remove from shopping list\n3.Print list of items\n4.Print total price\n5.Logout");
            switch (scanner.nextLine()) {
                case "1":
                    addToShoppingList();
                    break;
                case "2":
                    removeFromShoppingList();
                    break;
                case "3":
                    listAllItems();
            }
        }
    }

    public static void addToShoppingList() {
        int amount;
        int selection;
        List<Product> products = new ArrayList<>();
        products.addAll(Arrays.asList(Electronics.values()));
        products.addAll(Arrays.asList(Shoe.values()));
        System.out.println("Choose a product to add to your shopping list");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(i + " " + products.get(i).getName());
        }
        try {
            selection = Integer.parseInt(scanner.nextLine());
            System.out.println("How many do you want");
            amount = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Please enter a number!");
            return;
        }
        if (selection >= products.size()) {
            System.out.println("Not a valid selection");
            return;
        }
        Product product = products.get(selection);
        try {
            ApplicationContext.SHOPPING_LIST_SERVICE.save(new ShoppingList(product.getName(), amount, amount * 100));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        shop();
    }

    public static void removeFromShoppingList() {
        List<ShoppingList> shoppingList = new ArrayList<>();
        int selection;
        int validation = 0;
        try {
            shoppingList = ApplicationContext.SHOPPING_LIST_SERVICE.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < shoppingList.size(); i++) {
            System.out.println(shoppingList.get(i));
        }
        try {
            System.out.println("Enter the id of the product you want to remove");
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Please enter a number!");
            return;
        }
        try {
            validation = ApplicationContext.SHOPPING_LIST_SERVICE.delete(selection);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return;
        }
        if (validation != 0)
            System.out.println("Product deleted");
        else
            System.out.println("No Product was found or deleted");
    }
    public static void listAllItems(){
        List<ShoppingList> shoppingList = new ArrayList<>();
        try {
            shoppingList = ApplicationContext.SHOPPING_LIST_SERVICE.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < shoppingList.size(); i++) {
            System.out.println(shoppingList.get(i));
        }
    }

}
