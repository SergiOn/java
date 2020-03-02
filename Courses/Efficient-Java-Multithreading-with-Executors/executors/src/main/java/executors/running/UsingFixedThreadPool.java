package executors.running;

import common.LoopTaskA;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingFixedThreadPool {

    public static void main(String[] args) {
        System.out.println("Main thread starts here...");

//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());

        // it runs after thread pool will exist
        executorService.submit(new LoopTaskA());
        executorService.submit(new LoopTaskA());

        executorService.shutdown();

        // will be error
//        executorService.submit(new LoopTaskA());

        System.out.println("Main thread ends here...");
    }

}
