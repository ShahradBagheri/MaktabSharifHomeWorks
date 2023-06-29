import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println(Permutation.generatePermutations("cat"));
        System.out.println(Permutation.generatePermutations("cat act tac bac"));

        TreeSet<Character> treeSet1 = TreeSetQuestion.randomTenCharTree();
        TreeSet<Character> treeSet2 = TreeSetQuestion.randomTenCharTree();
        TreeSetQuestion.showTree(treeSet1);
        TreeSetQuestion.showTree(treeSet2);

        Set<Character> combine = TreeSetQuestion.combine(treeSet1, treeSet2);
        System.out.println(combine);

        Set<Character> common = TreeSetQuestion.common(treeSet1, treeSet2);
        System.out.println(common);

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 3, 7, 4, 3, 6, 5, 8, 5, 5, 2, 9, 7, 3));
        ArrayListFixer.removeNonCorrect(arrayList);
        System.out.println(arrayList);

        System.out.println();
        System.out.println();
        System.out.println();

        CustomHashmap<String, String> customHashmap = new CustomHashmap<>(2);
        System.out.println(customHashmap.isEmpty());
        try {
            customHashmap.put(null, "nice");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            customHashmap.put("one", "nice1");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            customHashmap.put("two", "nice2");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            customHashmap.put("two", "newNice2");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try{
            customHashmap.put("three","nice3");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        try{
            customHashmap.put("four","nice4");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println(customHashmap.get("one"));
        System.out.println(customHashmap.get("test"));
        System.out.println(customHashmap.getAll());
        System.out.println(customHashmap.getCapacity());
        System.out.println(customHashmap.getSize());
        System.out.println(customHashmap.isEmpty());
        System.out.println(customHashmap.containsKey("one"));
        System.out.println(customHashmap.containsKey("1"));
    }
}
