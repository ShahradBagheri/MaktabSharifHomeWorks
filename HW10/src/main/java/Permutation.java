import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutation {
    public static List<String> generatePermutations(String word) {
        String[] inputArgs = word.split(" ");

        if(inputArgs.length == 1){
            List<String> permutations = new ArrayList<>();
            Map<Character, Integer> charCounts = countCharacters(inputArgs[0]);
            actuallyGeneratePermutations(charCounts, "", inputArgs[0].length(), permutations);
            return permutations;
        }else if (inputArgs.length == 2){
            List<String> permutations = new ArrayList<>();
            Map<Character, Integer> charCounts = countCharacters(inputArgs[0]);
            actuallyGeneratePermutations(charCounts, "", inputArgs[0].length(), permutations);
            System.out.println(permutations.contains(inputArgs[1]));
            return permutations;
        }else {
            System.out.println("not a valid amount of inputs");
            return null;
        }
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

    public static void actuallyGeneratePermutations(Map<Character, Integer> charCounts, String prefix, int remainingLength, List<String> permutations) {
        if (remainingLength == 0) {
            permutations.add(prefix);
            return;
        }

        for (char c : charCounts.keySet()) {
            int count = charCounts.get(c);
            if (count > 0) {
                charCounts.put(c, count - 1);
                actuallyGeneratePermutations(charCounts, prefix + c, remainingLength - 1, permutations);
                charCounts.put(c, count);
            }
        }
    }
}
