package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(firstQuestion(MockData.getPeople()));
        System.out.println(secondQuestion(MockData.getPeople()));
        System.out.println(thirdQuestion(MockData.getPeople()));
        System.out.println(forthQuestion(MockData.getPeople()));
        System.out.println(fifthQuestion(MockData.getPeople()));
    }

    public static List<Person> firstQuestion(List<Person> data) {
        return data.stream().filter((person -> person.getAge() <= 50)).collect(Collectors.toList());
    }

    public static List<Person> secondQuestion(List<Person> data){
        return data.stream().sorted(Comparator.comparing(Person::getUsername)).collect(Collectors.toList());
    }

    public static List<Person> thirdQuestion(List<Person> data){
        return data.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getLastName)).collect(Collectors.toList());
    }

    public static Set<String> forthQuestion(List<Person> data){
        return data.stream().map(Person::getIpv4).collect(Collectors.toSet());
    }

    public static Map<String,List<Person> > fifthQuestion(List<Person> data){
        return data.stream().sorted(Comparator.comparing(Person::getLastName))
                .filter(person -> person.getGender().equals("Male") || person.getAge() <= 40)
                .dropWhile(person -> person.getFirstName().startsWith("A"))
                .skip(5)
                .limit(100)
                .collect(Collectors.groupingBy(person -> person.getFirstName()+person.getLastName()));
    }
}