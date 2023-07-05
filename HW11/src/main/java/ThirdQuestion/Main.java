package ThirdQuestion;

public class Main {
    public static void main(String[] args) {
        Runnable runnable1 = new FirstRunnable();
        Runnable runnable2 = new SecondRunnable();
        Thread thread1 = new Thread(runnable1);
        thread1.setPriority(10);
        Thread thread2 = new Thread(runnable1);
        thread1.setPriority(9);
        Thread thread3 = new Thread(runnable1);
        thread1.setPriority(8);
        Thread thread4 = new Thread(runnable2);
        thread2.setPriority(1);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
