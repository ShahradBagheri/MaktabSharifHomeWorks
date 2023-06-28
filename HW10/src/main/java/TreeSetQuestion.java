import java.util.Random;
import java.util.TreeSet;

public class TreeSetQuestion {

    public static TreeSet<Character> randomCharTree(){
        TreeSet<Character> treeSet = new TreeSet<>();
        Random random = new Random();

        while (treeSet.size() < 10) {
            char randomChar = (char) (random.nextInt(26) + 97);
            treeSet.add(randomChar);
        }
        return  treeSet;
    }
}
