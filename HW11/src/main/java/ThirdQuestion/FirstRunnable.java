package ThirdQuestion;

public class FirstRunnable implements Runnable {
    private final String name;

    public FirstRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (Main.SHARED_RESOURCE) {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " using shared resource");
            }
        }
    }
}
