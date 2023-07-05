package SecondQuestion;

public class Main {
    final static String STRING1 = "Nice";
    final static String STRING2 = "Not nice";
    public static void main(String[] args){
        Runnable firstDeadlockRunnable = new FirstDeadlockRunnable();
        Runnable secondDeadlockRunnable = new SecondDeadlockRunnable();
        Thread thread1 = new Thread(firstDeadlockRunnable);
        Thread thread2 = new Thread(secondDeadlockRunnable);

        thread1.start();
        thread2.start();
    }
}
