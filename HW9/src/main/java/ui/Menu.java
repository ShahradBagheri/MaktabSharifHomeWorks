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
        System.out.println("Enter national_code");
        String natCode = scanner.nextLine();
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
}
