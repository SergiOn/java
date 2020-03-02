package thread8;

public class App {

    public static void main(String[] args) throws InterruptedException {

        final Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}

/*
* Producer thread running...
* Waiting for return key
* <enter>
* Return key pressed
* Resumed
* */
