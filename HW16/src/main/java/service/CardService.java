package service;

import model.Card;
import model.Student;

public interface CardService {

    boolean validCardNumber(String cardNumber);

    boolean validCvv2(String cvv2);

    boolean correctCard(Student student, Card card);
}
