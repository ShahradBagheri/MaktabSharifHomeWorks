package FirstQuestion;

import java.util.List;

public class FirstRunnable implements Runnable{

    private final List<Integer> list;
    private final int listLength;

    public FirstRunnable(List<Integer> list, int listLength) {
        this.list = list;
        this.listLength = listLength;
    }


    @Override
    public void run() {
        synchronized (list) {
            for (int i = 1; i <= listLength; i += 2) {
                while (list.size() % 2 == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                list.add(i);
                list.notify();
            }
        }
    }
}
