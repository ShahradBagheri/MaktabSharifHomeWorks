import java.util.List;

public class SecondRunnable implements Runnable{

    private final List<Integer> list;
    private final int listLength;

    public SecondRunnable(List<Integer> list, int listLength) {
        this.list = list;
        this.listLength = listLength;
    }
    @Override
    public void run() {
        synchronized (list) {
            for (int i = 0; i <= listLength; i += 2) {
                while (list.size() % 2 == 1) {
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
