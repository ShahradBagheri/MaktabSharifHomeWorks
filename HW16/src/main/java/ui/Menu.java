package ui;

import enumeration.Degree;
import enumeration.UniversityType;
import model.*;
import util.ApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void run(){

        while (true){
            System.out.println("1.Login\n2.SignUp\n3.Exit");

            switch (scanner.nextLine()){
                case "1" -> login();
                case "2" -> signUp();
                case "3" -> System.exit(0);
                default -> System.out.println("Not a valid option");
            }
        }
    }

    public static void login(){
        System.out.println("government id");
        String governmentId = scanner.nextLine();

        System.out.println("password");
        String password = scanner.nextLine();

        Student student = ApplicationContext.studentService.login(governmentId,password);
        if (student == null)
            System.out.println("wrong password or government id");
        else studentPanel(student);
    }

    public static void studentPanel(Student student){
        while (true){
            System.out.println("1.get loan\n2.payments\n3.Exit");
            switch (scanner.nextLine()){
                case "1" -> {
                    if(!ApplicationContext.studentService.hasGraduated(student))
                        GetLoanPanel.getLoan(student);
                    else
                        System.out.println("you have graduated!");
                }
                case "2" -> {
                    if(ApplicationContext.studentService.hasGraduated(student))
                        PaymentPanel.payments(student);
                    else
                        System.out.println("you have not graduated!");
                }
                case "3" -> System.exit(0);
                default -> System.out.println("not a valid option");
            }
        }
    }


    public static void signUp(){
        System.out.println("firstname");
        String firstname = scanner.nextLine();

        System.out.println("lastname");
        String lastname = scanner.nextLine();

        System.out.println("father name");
        String fatherName = scanner.nextLine();

        System.out.println("mother name");
        String motherName = scanner.nextLine();

        System.out.println("social id");
        String socialId = scanner.nextLine();

        if(!ApplicationContext.studentService.socialIdValidation(socialId) || ApplicationContext.studentService.socialIdExists(socialId)){
            System.out.println("not a valid social id or already signed up");
            return;
        }

        System.out.println("government id");
        String governmentId = scanner.nextLine();

        if(!ApplicationContext.studentService.governmentIdValidation(governmentId) || ApplicationContext.studentService.governmentIdExists(governmentId)){
            System.out.println("not a valid government id or already signed up");
            return;
        }

        System.out.println("birthday (yyyy-mm-dd)");
        LocalDate birthday;

        try{
            birthday = LocalDate.parse(scanner.nextLine());
        }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("student id");
        String studentId = scanner.nextLine();

        if(!ApplicationContext.studentService.studentIdValidation(studentId) || ApplicationContext.studentService.studentIdExists(studentId)){
            System.out.println("not a valid student id or already signed up");
            return;
        }

        System.out.println("university name");
        String universityName = scanner.nextLine();

        boolean choosingUniversityType = true;
        UniversityType universityType = null;

        while (choosingUniversityType){
            System.out.println("select university type\n1.public(day)\n2.public(night)\n3.private\n4.pardis\n5.payame noor\n6.elmi carbordi\n7.azad");
            switch (scanner.nextLine()){
                case "1" -> {
                    universityType = UniversityType.PUBLIC_DAY;
                    choosingUniversityType = false;
                }
                case "2" -> {
                    universityType = UniversityType.PUBLIC_NIGHT;
                    choosingUniversityType = false;
                }
                case "3" -> {
                    universityType = UniversityType.PRIVATE;
                    choosingUniversityType = false;
                }
                case "4" -> {
                    universityType = UniversityType.PARDIS;
                    choosingUniversityType = false;
                }
                case "5" -> {
                    universityType = UniversityType.PAYAM_NOOR;
                    choosingUniversityType = false;
                }
                case "6" -> {
                    universityType = UniversityType.ELMI_CARBORDI;
                    choosingUniversityType = false;
                }
                case "7" -> {
                    universityType = UniversityType.AZAD;
                    choosingUniversityType = false;
                }
                default -> System.out.println("not a valid option");
            }
        }

        System.out.println("entrance year");
        int entranceYear = 0;

        try{
            entranceYear = Integer.parseInt(scanner.nextLine());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        if(!ApplicationContext.studentService.entranceYearValidation(entranceYear)){
            System.out.println("not a valid year");
            return;
        }

        boolean choosingDegree = true;
        Degree degree = null;

        while (choosingDegree){
            System.out.println("select degree\n1.collage\n2.bachelor(connected)\n3.bachelor(not connected)\n4.masters(connected)\n5.masters(not connected)\n6.professional PHD\n7.PHD(connected)\n8.PHD(not connected)");
            switch (scanner.nextLine()){
                case "1" -> {
                    degree = Degree.COLLAGE;
                    choosingDegree = false;
                }
                case "2" -> {
                    degree = Degree.BACHELOR_CONNECTED;
                    choosingDegree = false;
                }
                case "3" -> {
                    degree = Degree.BACHELOR_NOT_CONNECTED;
                    choosingDegree = false;
                }
                case "4" -> {
                    degree = Degree.MASTERS_CONNECTED;
                    choosingDegree = false;
                }
                case "5" -> {
                    degree = Degree.MASTERS_NOT_CONNECTED;
                    choosingDegree = false;
                }
                case "6" -> {
                    degree = Degree.PROFESSIONAL_PHD;
                    choosingDegree = false;
                }
                case "7" -> {
                    degree = Degree.PHD_CONNECTED;
                    choosingDegree = false;
                }
                case "9" -> {
                    degree = Degree.PHD_NOT_CONNECTED;
                    choosingDegree = false;
                }
                default -> System.out.println("not a valid option");
            }

            String password = ApplicationContext.studentService.generatePassword();
            System.out.println("your password is " + password);

            Student student = Student.builder()
                    .name(firstname)
                    .lastname(lastname)
                    .fatherName(fatherName)
                    .motherName(motherName)
                    .studentId(studentId)
                    .socialId(socialId)
                    .governmentId(governmentId)
                    .birthdate(birthday)
                    .universityName(universityName)
                    .universityType(universityType)
                    .degree(degree)
                    .entranceYear(entranceYear)
                    .password(password)
                    .build();

            student = ApplicationContext.studentService.signUp(student);

            if(student != null)
                System.out.println("student with id of " + student.getId() + " was created");
            else
                System.out.println("failed to create student");
        }
    }
}
