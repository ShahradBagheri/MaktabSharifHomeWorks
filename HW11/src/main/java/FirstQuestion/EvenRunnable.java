package FirstQuestion;

import java.util.List;

public class EvenRunnable implements Runnable {

    private final List<Integer> sharedList;
    private final List<Integer> givenList;

    public EvenRunnable(List<Integer> sharedList, List<Integer> givenList) {
        this.sharedList = sharedList;
        this.givenList = givenList;
    }

    @Override
    public void run() {
        synchronized (sharedList) {
            for (Integer evenNumber : givenList) {
                while (sharedList.size() % 2 == 1) {
                    try {
                        sharedList.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                sharedList.add(evenNumber);
                sharedList.notify();
            }
        }
    }
}
