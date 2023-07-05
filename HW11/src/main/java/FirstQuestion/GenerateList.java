package FirstQuestion;

import java.util.ArrayList;
import java.util.List;

public class GenerateList {

    public List<Integer> generateOddList(int number){
        List<Integer> output = new ArrayList<>();
        for (int i = 1; i <= number; i += 2) {
            output.add(i);
        }
        return output;
    }

    public List<Integer> generateEvenList(int number){
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i <= number; i += 2) {
            output.add(i);
        }
        return output;
    }
}
