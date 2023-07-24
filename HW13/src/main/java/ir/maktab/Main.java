package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(firstQuestion(MockData.getPeople()));
        System.out.println(secondQuestion(MockData.getPeople()));
    }

    public static List<Person> firstQuestion(List<Person> data) {
        return data.stream().filter((person -> person.getAge() <= 50)).collect(Collectors.toList());
    }

    public static List<Person> secondQuestion(List<Person> data){
        return data.stream().sorted(Comparator.comparing(Person::getUsername)).collect(Collectors.toList());
    }
}