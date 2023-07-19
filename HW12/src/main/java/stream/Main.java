package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Amir","Hataf","Mahdi","Mohammad","Ali","Taher","Reza","Mohsen");

        List<String> sortedList = list.stream().sorted(Comparator.comparing(String::length)).toList();
        System.out.println(sortedList);

        Map<Integer, List<String>> map = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(map);
    }
}
