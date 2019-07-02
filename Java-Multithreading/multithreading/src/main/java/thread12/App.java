package thread12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

//    public static void main(String[] args) throws Exception {
//
//        Semaphore semaphore = new Semaphore(1);
//
////        semaphore.release();
//        semaphore.acquire();
//        semaphore.release();
//
//        System.out.println("Available permits: " + semaphore.availablePermits());
//    }

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {

            executor.submit(new Thread(() -> {

                Connection.getInstance().connect();
            }));
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
