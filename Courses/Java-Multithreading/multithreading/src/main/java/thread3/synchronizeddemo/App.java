package thread3.synchronizeddemo;

public class App {

    private int count = 0;

    // volatile doesn't help
    // private volatile int count = 0;

    private synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {

        App app = new App();
        app.doWork();
    }

    public void doWork() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                count++;
                // count = count + 1
                // 3 steps: get count, sum count, assign count

                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                count++;
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
        System.out.println("Count equal: " + (count == 20000));

    }
}
