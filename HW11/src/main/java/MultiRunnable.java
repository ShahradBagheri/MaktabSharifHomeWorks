import java.util.List;

public class MultiRunnable implements Runnable {

    private final List<Integer> list;
    private final int listLength;
    private final int remainder;

    public MultiRunnable(List<Integer> list, int listLength, boolean even) {
        this.list = list;
        this.listLength = listLength;
        this.remainder = even ? 0 : 1;
    }


    @Override
    public void run() {
        synchronized (list) {
            for (int i = 1-remainder; i <= listLength; i += 2) {
                while (list.size() % 2 == remainder) {
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
