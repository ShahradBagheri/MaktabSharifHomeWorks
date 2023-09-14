package ui;

import enumeration.BankName;
import enumeration.Degree;
import enumeration.LoanType;
import enumeration.UniversityType;
import model.*;
import util.ApplicationContext;
import util.Constant;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
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
        System.out.println("1.get loan\n2.payments\n3.Exit");
        while (true){
            switch (scanner.nextLine()){
                case "1" -> {
                    if(!ApplicationContext.studentService.hasGraduated(student))
                        getLoan(student);
                    else
                        System.out.println("you have graduated!");
                }
                case "2" -> {
                    if(ApplicationContext.studentService.hasGraduated(student))
                        payments(student);
                    else
                        System.out.println("you have not graduated!");
                }
                case "3" -> System.exit(0);
                default -> System.out.println("not a valid option");
            }
        }
    }

    public static void getLoan(Student student){

        if(!ApplicationContext.loanService.isTimeToGetLoan()){
            System.out.println("its not time to get a loan yet!");
            return;
        }

        System.out.println("what loan do you want to get\n1.tuition fee\n2.studying\n3.rent");
        switch (scanner.nextLine()){
            case "1" -> getTuitionFeeLoan(student);
            case "2" -> getStudyingLoan(student);
            case "3" -> getRentLoan(student);
            default -> System.out.println("not a valid option");
        }
    }

    public static void getStudyingLoan(Student student){

        if (ApplicationContext.loanService.gotStudyLoanThisSemester(student)){
            System.out.println("you already got a studying loan this semester");
            return;
        }

        if(student.getCard() == null){
            setCardMenu(student);
            student = ApplicationContext.studentService.findById(student.getId());
            if(student.getCard() == null){
                System.out.println("failed to add card");
                return;
            }
        }

        Loan loan = Loan.builder()
                .loanType(LoanType.STUDYING)
                .submitDate(Constant.DATE)
                .student(student)
                .build();

        loan = ApplicationContext.loanService.create(loan);
        if(loan == null)
            System.out.println("failed to get loan");
        else
            System.out.println("loan added");
    }

    public static void getTuitionFeeLoan(Student student){

        if (!ApplicationContext.loanService.canGetTuitionFeeLoan(student)){
            System.out.println("you cant get tuition fee loans");
            return;
        }

        if(ApplicationContext.loanService.gotTuitionFeeLoanThisSemester(student)){
            System.out.println("you already got a tuition fee loan this semester");
            return;
        }

        if(student.getCard() == null){
            setCardMenu(student);
            student = ApplicationContext.studentService.findById(student.getId());
            if(student.getCard() == null){
                System.out.println("failed to add card");
                return;
            }
        }

        Loan loan = Loan.builder()
                .loanType(LoanType.TUITION_FEE)
                .submitDate(Constant.DATE)
                .student(student)
                .build();

        loan = ApplicationContext.loanService.create(loan);
        if(loan == null)
            System.out.println("failed to get loan");
        else
            System.out.println("loan added");
    }

    public static void getRentLoan(Student student){

        if(ApplicationContext.loanService.gotRentLoan(student)){
            System.out.println("you already got a rent loan");
            return;
        }

        if(student.getRentContract() == null){

            System.out.println("enter city name");
            String city = scanner.nextLine();

            System.out.println("enter address");
            String address = scanner.nextLine();

            System.out.println("enter contract number");
            String contractNumber = scanner.nextLine();
            if(!ApplicationContext.rentContractService.validContractNumber(contractNumber)){
                System.out.println("not a valid contract number");
                return;
            }

            RentContract rentContract = RentContract.builder()
                    .city(city)
                    .address(address)
                    .contractNumber(contractNumber)
                    .build();

            student.setRentContract(rentContract);
            student = ApplicationContext.studentService.update(student);

            if(student.getRentContract() != null) {
                System.out.println("contract added");
            }
            else{
                System.out.println("failed to add contract");
                return;
            }
        }


        if(student.getSpouse() == null){
            Student spouse = null;

            boolean choosingSpouse = true;
            while (choosingSpouse){
                System.out.println("do you have a spouse?\nYes\nNo");
                switch (scanner.nextLine()){
                    case "Yes" -> {
                        System.out.println("login with your spouse account here\ngovernment id");
                        String governmentId = scanner.nextLine();

                        System.out.println("password");
                        String password = scanner.nextLine();

                        spouse = ApplicationContext.studentService.login(governmentId,password);
                        if(spouse == null){
                            System.out.println("wrong password or government id\nCouldn't add spouse to your account");
                        }
                    }
                    case "No" -> choosingSpouse = false;

                    default -> System.out.println("not a valid option+");
                }

            }
            student.setSpouse(spouse);
            student = ApplicationContext.studentService.update(student);
        }

        if(ApplicationContext.loanService.livesInBigCIty(student) && student.getSpouse() == null){
            System.out.println("you have to be married to get a rent loan in your city");
            return;
        }

        if(ApplicationContext.loanService.spouseGotRentLoan(student)){
            System.out.println("your spouse already got a rent loan");
            return;
        }

        if(student.getCard() == null){
            setCardMenu(student);
            student = ApplicationContext.studentService.findById(student.getId());
            if(student.getCard() == null){
                System.out.println("failed to add card");
                return;
            }
        }

        Loan loan = Loan.builder()
                .loanType(LoanType.RENT)
                .submitDate(Constant.DATE)
                .student(student)
                .build();

        loan = ApplicationContext.loanService.create(loan);
        if(loan == null)
            System.out.println("failed to get loan");
        else
            System.out.println("loan added");
    }

    public static void payments(Student student){
        System.out.println("1.show paid payments\n2.show unpaid payments\n3.pay payment");
        switch (scanner.nextLine()){
            case "1" -> showPaidPayments(student);
            case "2" -> showUnpaidPayments(student);
            case "3" -> payPayment(student);
            default -> System.out.println("not a valid option");
        }
    }

    public static void showPaidPayments(Student student){

        List<Payment> payments = ApplicationContext.paymentService.getPaidPayments(student);

        payments.forEach(System.out::println);
    }

    public static void showUnpaidPayments(Student student){

        List<Payment> payments = ApplicationContext.paymentService.getUnpaidPayments(student);

        payments.forEach(System.out::println);
    }

    public static void payPayment(Student student){

        System.out.println("give payment id");
        long paymentId;
        try{
            paymentId = Long.parseLong(scanner.nextLine());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        if(!ApplicationContext.paymentService.studentOwnsPayment(student,paymentId)){
            System.out.println("you dont own this payment");
            return;
        }

        Card card;

        boolean choosingBank = true;
        BankName bankName = null;
        while (choosingBank){
            System.out.println("choose bank\n1.Meli\n2.Refah\n3.Tejarat\n4.Maskan");
            switch (scanner.nextLine()){
                case "1" -> {
                    bankName = BankName.MELI;
                    choosingBank = false;
                }
                case "2" -> {
                    bankName = BankName.REFAH;
                    choosingBank = false;
                }
                case "3" -> {
                    bankName = BankName.TEJARAT;
                    choosingBank = false;
                }
                case "4" -> {
                    bankName = BankName.MASKAN;
                    choosingBank = false;
                }
                default -> System.out.println("not a valid option");
            }
        }

        System.out.println("Enter card number");
        String cardNumber = scanner.nextLine();
        if (!ApplicationContext.cardService.validCardNumber(cardNumber)){
            System.out.println("not a valid card number");
            return;
        }

        System.out.println("Enter cvv2");
        String cvv2 = scanner.nextLine();
        if(!ApplicationContext.cardService.validCvv2(cvv2)){
            System.out.println("not a valid cvv2");
            return;
        }

        System.out.println("expiration date (yyyy-mm-dd)");
        LocalDate expirationDate;

        try{
            expirationDate = LocalDate.parse(scanner.nextLine());
        }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            return;
        }

        card = Card.builder()
                .student(student)
                .bankName(bankName)
                .cardNumber(cardNumber)
                .cvv2(cvv2)
                .expirationDate(expirationDate)
                .build();

        if(!ApplicationContext.cardService.correctCard(student,card))
            System.out.println("not the correct card");
        else{
            Payment payment = ApplicationContext.paymentService.findById(paymentId);
            payment.setIsPaid(true);
            payment = ApplicationContext.paymentService.update(payment);
            if(payment != null)
                System.out.println("paid payment");
            else
                System.out.println("couldn't pay payment");
        }
    }

    public static void setCardMenu(Student student){

        Card card;

        boolean choosingBank = true;
        BankName bankName = null;
        while (choosingBank){
            System.out.println("choose bank\n1.Meli\n2.Refah\n3.Tejarat\n4.Maskan");
            switch (scanner.nextLine()){
                case "1" -> {
                    bankName = BankName.MELI;
                    choosingBank = false;
                }
                case "2" -> {
                    bankName = BankName.REFAH;
                    choosingBank = false;
                }
                case "3" -> {
                    bankName = BankName.TEJARAT;
                    choosingBank = false;
                }
                case "4" -> {
                    bankName = BankName.MASKAN;
                    choosingBank = false;
                }
                default -> System.out.println("not a valid option");
            }
        }

        System.out.println("Enter card number");
        String cardNumber = scanner.nextLine();
        if (!ApplicationContext.cardService.validCardNumber(cardNumber)){
            System.out.println("not a valid card number");
            return;
        }

        System.out.println("Enter cvv2");
        String cvv2 = scanner.nextLine();
        if(!ApplicationContext.cardService.validCvv2(cvv2)){
            System.out.println("not a valid cvv2");
            return;
        }

        System.out.println("expiration date (yyyy-mm-dd)");
        LocalDate expirationDate;

        try{
            expirationDate = LocalDate.parse(scanner.nextLine());
        }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            return;
        }

        card = Card.builder()
                .student(student)
                .bankName(bankName)
                .cardNumber(cardNumber)
                .cvv2(cvv2)
                .expirationDate(expirationDate)
                .build();

        student.setCard(card);
        ApplicationContext.studentService.update(student);
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
