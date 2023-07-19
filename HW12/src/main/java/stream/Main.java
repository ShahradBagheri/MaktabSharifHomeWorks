package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Amir", "Hataf", "Mahdi", "Mohammad", "Ali", "Taher", "Reza", "Mohsen");

        Map<Integer, List<String>> map = list.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(map);
    }
}
