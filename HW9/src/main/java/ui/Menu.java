package ui;

import model.User;
import util.ApplicationContext;

import java.sql.SQLException;
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
    public static void signupMenu(){
        System.out.println("Enter firstname");
        String firstname = scanner.nextLine();
        System.out.println("Enter lastname");
        String lastname = scanner.nextLine();
        String username = validateUsername();
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        String natCode = validateNatCode();
        String email = validateEmail();
        try{
            ApplicationContext.USER_SERVICE.save(new User(firstname,lastname,username,password,natCode,email));
            System.out.println("You have signed up");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static String validateUsername() {
        String username;
        while (true){
            System.out.println("Enter username");
            username = scanner.nextLine();
            try{
                if (!ApplicationContext.USER_SERVICE.isExistsUsername(username))
                    break;
            }catch (Throwable e){
                System.out.println(e.getMessage());
            }
        }
        return username;
    }
    public static String validateEmail(){
        String email;
        while (true){
            System.out.println("Enter email");
            email = scanner.nextLine();
            try{
                if (!ApplicationContext.USER_SERVICE.isExistsEmail(email))
                    break;
            }catch (Throwable e){
                System.out.println(e.getMessage());
            }
        }
        return email;
    }
    public static String validateNatCode(){
        String natCode;
        while (true){
            System.out.println("Enter national code");
            natCode = scanner.nextLine();
            try{
                if (!ApplicationContext.USER_SERVICE.isExistsNatCode(natCode))
                    break;
            }catch (Throwable e){
                System.out.println(e.getMessage());
            }
        }
        return natCode;
    }
    public static void loginMenu(){
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        try{
            User loggedInUser = ApplicationContext.USER_SERVICE.checkCredentialInfoForLogin(username,password);
            dashboardMenu();
        }catch (Throwable e){
            System.out.println(e.getMessage());
        }
    }
    public static void dashboardMenu(){
        System.out.println("nice");
    }
}
