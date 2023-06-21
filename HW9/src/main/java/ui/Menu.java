package ui;

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

    }
}
