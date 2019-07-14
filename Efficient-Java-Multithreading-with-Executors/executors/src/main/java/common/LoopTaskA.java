package common;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable {

    private static int count = 0;
    private int id;

    public LoopTaskA() {
        id = ++count;
    }

    @Override
    public void run() {
        System.out.println("##### <TASK-" + id + "> STARTING #####");
        Random random = new Random();

        for (int i = 10; i > 0; i--) {
            System.out.println("<TASK-" + id + "> TICK TICK " + i);

            try {
                TimeUnit.MICROSECONDS.sleep(random.nextInt(1000000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("***** <TASK-" + id + "> DONE *****");
    }
}
