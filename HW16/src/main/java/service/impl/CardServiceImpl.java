package service.impl;

import model.Card;
import model.Student;
import service.CardService;

import java.util.regex.Pattern;

public class CardServiceImpl implements CardService {

    @Override
    public boolean validCardNumber(String cardNumber) {
        return Pattern.matches("[0-9]{12}",cardNumber);
    }

    @Override
    public boolean validCvv2(String cvv2) {
        return Pattern.matches("[0-9]{4}",cvv2);
    }

    @Override
    public boolean correctCard(Student student, Card card) {
        System.out.println(card);
        System.out.println(student.getCard());
        return student.getCard().getCardNumber().equals(card.getCardNumber())
                && student.getCard().getCvv2().equals(card.getCvv2())
                && student.getCard().getBankName().equals(card.getBankName())
                && student.getCard().getExpirationDate().equals(card.getExpirationDate());
    }
}
