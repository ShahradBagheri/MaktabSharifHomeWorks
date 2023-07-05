package SecondQuestion;

import java.util.List;

public class FirstRunnable implements Runnable {
    List<String> firstList;
    List<String> secondList;

    public FirstRunnable(List<String> firstList, List<String> secondList) {
        this.firstList = firstList;
        this.secondList = secondList;
    }

    @Override
    public void run() {
        synchronized (firstList) {
            firstList.add("first runnable signature");
        }
        synchronized (secondList){
            secondList.add("second runnable signature");
        }
    }
}
