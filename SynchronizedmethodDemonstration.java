package multithreadingconcept;

import java.util.concurrent.Semaphore;

class Chopstick {
    private final Semaphore semaphore;

    public Chopstick() {
        this.semaphore = new Semaphore(1); // Each chopstick is a binary semaphore
    }

    public void pickUp() throws InterruptedException {
        semaphore.acquire(); // Try to acquire the chopstick
    }

    public void putDown() {
        semaphore.release(); // Release the chopstick
    }
}

class Philosopher extends Thread {
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;
    private final int id;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000)); // Simulate thinking time
    }

    private void eat() throws InterruptedException {
        // Pick up the chopsticks
        leftChopstick.pickUp();
        rightChopstick.pickUp();

        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((long) (Math.random() * 1000)); // Simulate eating time

        // Put down the chopsticks
        rightChopstick.putDown();
        leftChopstick.putDown();
        System.out.println("Philosopher " + id + " has finished eating.");
    }
}

public class SynchronizedmethodDemonstration {
    public static void main(String[] args) {
        final int NUMBER_OF_PHILOSOPHERS = 5;
        Chopstick[] chopsticks = new Chopstick[NUMBER_OF_PHILOSOPHERS];
        Philosopher[] philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];

        // Create chopsticks
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            chopsticks[i] = new Chopstick();
        }

        // Create philosophers and assign chopsticks
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUMBER_OF_PHILOSOPHERS]);
            philosophers[i].start();
        }

        // Optionally, join philosophers to the main thread to keep the program running
        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

