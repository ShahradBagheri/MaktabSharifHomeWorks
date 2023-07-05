package FirstQuestion;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        GenerateList generateList = new GenerateList();
        try{
            int number = scanner.nextInt();
            Runnable evenRunnable = new EvenRunnable(list,generateList.generateEvenList(number));
            Runnable oddRunnable = new OddRunnable(list,generateList.generateOddList(number));

            Thread thread1 = new Thread(evenRunnable);
            Thread thread2 = new Thread(oddRunnable);

            thread1.start();
            thread2.start();

            thread2.join();

            System.out.println(list);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }


    }
}
