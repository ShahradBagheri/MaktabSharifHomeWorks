package ui;

import util.ApplicationContext;

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
}
