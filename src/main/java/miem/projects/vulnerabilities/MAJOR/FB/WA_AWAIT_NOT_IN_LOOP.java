package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WA_AWAIT_NOT_IN_LOOP {
    public static void main(String[] args) {
        System.out.println("Running incorrect test...");
        incorrectTest();

        System.out.println("\nRunning correct test...");
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private final Lock lock = new ReentrantLock();
            private final Condition condition = lock.newCondition();

            public void waitForCondition() throws InterruptedException {
                lock.lock();
                try {
                    condition.await(); // await() вне цикла
                    System.out.println("Condition met, proceeding...");
                } finally {
                    lock.unlock();
                }
            }

            public void signal() {
                lock.lock();
                try {
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        }

        Test test = new Test();
        Thread waitingThread = new Thread(() -> {
            try {
                test.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        waitingThread.start();

        try {
            Thread.sleep(1000); // Даем время потоку заснуть
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        test.signal(); // Пробуждаем поток
    }

    public static void correctTest() {
        class Test {
            private final Lock lock = new ReentrantLock();
            private final Condition condition = lock.newCondition();
            private boolean ready = false;

            public void waitForCondition() throws InterruptedException {
                lock.lock();
                try {
                    while (!ready) { // проверка условия в цикле
                        condition.await();
                    }
                    System.out.println("Condition met, proceeding...");
                } finally {
                    lock.unlock();
                }
            }

            public void setReady() {
                lock.lock();
                try {
                    ready = true;
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        }

        Test test = new Test();
        Thread waitingThread = new Thread(() -> {
            try {
                test.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        waitingThread.start();

        try {
            Thread.sleep(1000); // Даем время потоку заснуть
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        test.setReady(); // Пробуждаем поток
    }
}
