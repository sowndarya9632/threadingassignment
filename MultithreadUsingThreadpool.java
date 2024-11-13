package synchronizeconcept;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyTask implements Callable<String> {
    private final int taskId;

    public MyTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() {
        System.out.println("Executing task ID: " + taskId);
        try {
            // Simulating some work with sleep
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            return "Task interrupted: " + taskId;
        }
        return "Task ID " + taskId + " completed.";
    }
}

public class MultithreadUsingThreadpool {
    public static void main(String[] args) {
        // Create a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Submit tasks to the executor and store Futures
        Future<String>[] futures = new Future[10];
        for (int i = 0; i < 10; i++) {
            futures[i] = executor.submit(new MyTask(i+1));
        }

        // Shut down the executor
        executor.shutdown();
        try {
            // Wait for all tasks to finish and print results
            for (Future future : futures) {
                System.out.println(future.get()); // get() waits for the task to complete
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed.");
    }
}
