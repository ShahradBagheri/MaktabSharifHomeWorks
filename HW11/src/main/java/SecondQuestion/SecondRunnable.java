package SecondQuestion;

import java.util.List;

public class SecondRunnable implements Runnable{
    List<String> firstList;
    List<String> secondList;

    public SecondRunnable(List<String> firstList, List<String> secondList) {
        this.firstList = firstList;
        this.secondList = secondList;
    }

    @Override
    public void run() {
        synchronized (secondList) {
            secondList.add("first runnable signature");
            try {
                secondList.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        synchronized (firstList){
            firstList.add("second runnable signature");
        }
    }
}