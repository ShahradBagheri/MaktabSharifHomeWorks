import java.util.List;

public class SecondRunnable implements Runnable{

    private List<Integer> list;
    private int listLength;

    public SecondRunnable(List<Integer> list, int listLength) {
        this.list = list;
        this.listLength = listLength;
    }


    @Override
    public void run() {

    }
}
