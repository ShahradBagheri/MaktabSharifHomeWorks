package ui;

import enumeration.BankName;
import model.Card;
import model.Payment;
import model.Student;
import util.ApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PaymentPanel {

    static Scanner scanner = new Scanner(System.in);

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

        if(ApplicationContext.paymentService.isPaid(paymentId)){
            System.out.println("you have already paid this payment");
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
}
