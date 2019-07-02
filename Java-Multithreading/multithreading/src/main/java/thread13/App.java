package thread13;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

//        executor.submit(() -> {
//
//            Random random = new Random();
//            int duration = random.nextInt(4000);
//
//            System.out.println("Starting...");
//
//            try {
//                Thread.sleep(duration);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("Finished...");
//        });

//        executor.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return null;
//            }
//        });

        Future<Integer> future = executor.submit((Callable<Integer>) () -> {
//        Future<?> future = executor.submit((Callable<Void>) () -> {

            Random random = new Random();
            int duration = random.nextInt(4000);

            System.out.println("duration: " + duration);

            if (duration > 2000) {
                throw new IOException("Sleeping for too long");
            }

            System.out.println("Starting...");

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Finished...");

            return duration;
//            return null;
        });

        executor.shutdown();

//        future.cancel(true);
//        future.cancel(false);

//        try {
//            executor.awaitTermination(1, TimeUnit.DAYS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());

            IOException ex = (IOException) e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
