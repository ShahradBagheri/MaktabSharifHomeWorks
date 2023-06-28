import java.util.Random;
import java.util.Set;
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
    public static void showTree(TreeSet<Character> treeSet){
        for (Character character : treeSet) {
            System.out.print(character + " ");
        }
        System.out.println();
    }
    public static Set<Character> combine(TreeSet<Character> treeSet1, TreeSet<Character> treeSet2) {
        Set<Character> unionSet = new TreeSet<>(treeSet1);
        unionSet.addAll(treeSet2);
        return unionSet;
    }
    public static Set<Character> common(TreeSet<Character> treeSet1, TreeSet<Character> treeSet2) {
        Set<Character> unionSet = new TreeSet<>(treeSet1);
        unionSet.retainAll(treeSet2);
        return unionSet;
    }
}
