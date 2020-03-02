package thread2.volatiledemo;

import java.util.Scanner;

class Processor extends Thread {

    // can be cached by java
//    private boolean running = true;

    // volatile guarantee to work correctly between threads
    private volatile boolean running = true;

//    public volatile boolean running = true;

    @Override
    public void run() {

        while (running) {
            System.out.println("Hello");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {

    public static void main(String[] args) {

        Processor processor1 = new Processor();
        processor1.start();

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor1.shutdown();
//        processor1.running = false;
    }
}
