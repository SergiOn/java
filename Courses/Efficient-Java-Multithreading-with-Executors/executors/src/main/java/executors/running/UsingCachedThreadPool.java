package executors.running;

import common.LoopTaskA;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingCachedThreadPool {

    public static void main(String[] args) {
        System.out.println("Main thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());

        executorService.shutdown();

        System.out.println("Main thread ends here...");
    }

}
