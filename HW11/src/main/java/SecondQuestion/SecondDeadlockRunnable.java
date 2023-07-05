package SecondQuestion;

public class SecondDeadlockRunnable implements Runnable{
    @Override
    public void run() {
        synchronized (Main.STRING2){
            System.out.println("locking second string");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (Main.STRING1){
                System.out.println("locking first string now");
            }
        }
    }
}
