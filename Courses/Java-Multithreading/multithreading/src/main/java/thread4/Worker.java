package thread4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

//    public synchronized void stageOne() {
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        list1.add(random.nextInt(100));
//    }
//
//    public synchronized void stageTwo() {
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        list2.add(random.nextInt(100));
//    }

    public void stageOne() {

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {

        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

//        process();

        Thread t1 = new Thread(() -> {
            process();
        });
        t1.start();

        Thread t2 = new Thread(this::process);
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time take: " + (end - start));
        System.out.println("List 1: " + list1.size() + "; " + "List 2: " + list2.size());
    }
}
