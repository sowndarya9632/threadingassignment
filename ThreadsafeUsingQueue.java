package synchronizeconcept;

class Tasks implements Runnable {
	private final String taskName;

	public Tasks(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				// Check if the thread has been interrupted
				if (Thread.currentThread().isInterrupted()) {
					System.out.println(taskName + " was interrupted before completing the task!");
					return; // Exit the method if interrupted
				}

				// Simulate some work
				System.out.println(taskName + " is working... (Step " + (i + 1) + ")");
				Thread.sleep(1000); // Sleep for 1 second
			}
		} catch (InterruptedException e) {
			// Handle the interruption
			System.out.println(taskName + " was interrupted during sleep!");
			// Optionally, we can restore the interrupted status
			Thread.currentThread().interrupt();
		} finally {
			// Final cleanup code
			System.out.println(taskName + " has finished execution.");
		}
	}
}

public class ThreadsafeUsingQueue {
	public static void main(String[] args) {
		Thread taskThread = new Thread(new Tasks("Task 1"));

		// Start the thread
		taskThread.start();

		// Let the task run for a short time
		try {
			Thread.sleep(3000); // Main thread sleeps for 3 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Interrupt the task thread
		System.out.println("Main thread is interrupting Task 1...");
		taskThread.interrupt();

		// Wait for the task thread to finish
		try {
			taskThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Main thread has finished execution.");
	}
}
