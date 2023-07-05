package ThirdQuestion;

public class Main {
    public static final String SHARED_RESOURCE = "0";

    public static void main(String[] args) {
        Runnable runnable1 = new FirstRunnable("Runnable 1");
        Runnable runnable2 = new FirstRunnable("Runnable 2");
        Runnable runnable3 = new FirstRunnable("Runnable 3");

        Thread thread1 = new Thread(runnable1);
        thread1.setPriority(10);
        Thread thread2 = new Thread(runnable2);
        thread2.setPriority(9);
        Thread thread3 = new Thread(runnable3);
        thread3.setPriority(5);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
