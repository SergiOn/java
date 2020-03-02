package thread1.demo1;

class Runner extends Thread {

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

        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();
    }
}
