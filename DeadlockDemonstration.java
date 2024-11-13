package multithreadingconcept;

class Resource {
	private String name;

	public Resource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

class DeadlockThread extends Thread {
	private Resource resource1;
	private Resource resource2;

	public DeadlockThread(Resource resource1, Resource resource2) {
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	@Override
	public void run() {
		synchronized (resource1) {
			System.out.println(Thread.currentThread().getName() + " locked " + resource1.getName());

			// Simulate some work with the resource
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			synchronized (resource2) {
				System.out.println(Thread.currentThread().getName() + " locked " + resource2.getName());
			}
		}
	}
}

public class DeadlockDemonstration {
	public static void main(String[] args) {
		Resource resourceA = new Resource("Resource A");
		Resource resourceB = new Resource("Resource B");

		DeadlockThread thread1 = new DeadlockThread(resourceA, resourceB);
		DeadlockThread thread2 = new DeadlockThread(resourceB, resourceA);

		thread1.start();
		thread2.start();
	}
}
