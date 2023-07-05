package SecondQuestion;

public class FirstDeadlockRunnable implements Runnable {

    @Override
    public void run() {
        synchronized (Main.STRING1){
            System.out.println("locking first string");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (Main.STRING2){
                System.out.println("locking second string now");
            }
        }
    }
}
