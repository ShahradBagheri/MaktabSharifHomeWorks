import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutation {
    public static List<String> generatePermutations(String word) {
        List<String> permutations = new ArrayList<>();
        Map<Character, Integer> charCounts = countCharacters(word);
        return null;
    }

    public static Map<Character, Integer> countCharacters(String word) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (char c : word.toCharArray()) {
            if (charCounts.containsKey(c)) {
                int count = charCounts.get(c);
                charCounts.put(c, count + 1);
            } else {
                charCounts.put(c, 1);
            }
        }
        return charCounts;
    }
}
