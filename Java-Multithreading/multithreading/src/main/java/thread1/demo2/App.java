package thread1.demo2;

class Runner implements Runnable {

    private static int count = 1;
    private int number;

    public Runner() {
        number = count;
        count++;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            System.out.println("Runner: " + number + " | Hello: " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runner());
        Thread thread2 = new Thread(new Runner());

        thread1.start();
        thread2.start();
    }
}
