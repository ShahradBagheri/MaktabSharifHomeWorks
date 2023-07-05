package FirstQuestion;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try{
            int listLength = scanner.nextInt();
            Runnable firstRunnable = new FirstRunnable(list,listLength);
            Runnable secondRunnable = new SecondRunnable(list,listLength);
            Thread thread1 = new Thread(firstRunnable);
            Thread thread2 = new Thread(secondRunnable);

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println(list);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }


    }
}
