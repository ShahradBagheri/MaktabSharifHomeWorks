import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListFixer {
    public static void removeNonCorrect(List<Integer> list) {
        if (list.size() % 2 != 0)
            list.remove(list.size() - 1);

        for (int i = list.size() - 2; i >= 0; i -= 2) {
            if (list.get(i) < list.get(i + 1)) {
                list.remove(i + 1);
                list.remove(i);
            }
        }
    }
}
