package ThirdQuestion;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final String SHARED_RESOURCE = "0";

    public static void main(String[] args) {
        Runnable runnable1 = new FirstRunnable("Runnable 1");
        Runnable runnable2 = new FirstRunnable("Runnable 2");
        Runnable runnable3 = new FirstRunnable("Runnable 3");

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
