package synchronizeconcept;

class ThreadLocalVariable {
    // Create a ThreadLocal variable
    private static ThreadLocal<String> threadLocalValue = ThreadLocal.withInitial(() -> "Initial Value");

    public void setValue(String value) {
        threadLocalValue.set(value);
    }

    public String getValue() {
        return threadLocalValue.get();
    }

    public void removeValue() {
        threadLocalValue.remove();
    }
}

class MyRunnable implements Runnable {
    private final ThreadLocalVariable threadLocalVariable;

    public MyRunnable(ThreadLocalVariable threadLocalVariable) {
        this.threadLocalVariable = threadLocalVariable;
    }

    @Override
    public void run() {
        // Set a new value for the thread-local variable
        String threadName = Thread.currentThread().getName();
        threadLocalVariable.setValue(threadName + "'s Value");

        // Simulate some processing
        System.out.println(threadName + ": " + threadLocalVariable.getValue());

        // Remove the value from thread-local storage
        threadLocalVariable.removeValue();
    }
}
public class UsingThreadlocalvariable {
    public static void main(String[] args) {
        ThreadLocalVariable threadLocalVariable = new ThreadLocalVariable();

        // Create and start multiple threads
        Thread thread1 = new Thread(new MyRunnable(threadLocalVariable), "Thread-1");
        Thread thread2 = new Thread(new MyRunnable(threadLocalVariable), "Thread-2");
        

        thread1.start();
        thread2.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads have finished execution.");
    }
}

