package FirstQuestion;

import java.util.List;

public class OddRunnable implements Runnable{

    private final List<Integer> sharedList;
    private final List<Integer> givenList;

    public OddRunnable(List<Integer> sharedList, List<Integer> givenList) {
        this.sharedList = sharedList;
        this.givenList = givenList;
    }

    @Override
    public void run() {
        synchronized (sharedList) {
            for (Integer oddNumber : givenList) {
                while (sharedList.size() % 2 == 0) {
                    try {
                        sharedList.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                sharedList.add(oddNumber);
                notify();
            }
        }
    }
}
