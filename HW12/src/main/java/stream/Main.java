package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Amir", "Hataf", "Mahdi", "Mojtaba" , "Mohammad", "Ali", "Taher", "Reza", "Mohsen");

        Map<Integer, List<String>> map1 = list.stream()
                .collect(groupingBy(String::length));
        Map<Integer, Long> map2 = list.stream()
                .collect(groupingBy(String::length, counting()));

        System.out.println(map1);
        System.out.println(map2);
    }
}
