package SecondQuestion;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();

        Runnable firstRunnable = new FirstRunnable(firstList,secondList);
        Runnable secondRunnable = new FirstRunnable(firstList,secondList);
        Thread thread1 = new Thread(firstRunnable);
        Thread thread2 = new Thread(secondRunnable);

        thread1.start();
        thread2.start();
    }
}
