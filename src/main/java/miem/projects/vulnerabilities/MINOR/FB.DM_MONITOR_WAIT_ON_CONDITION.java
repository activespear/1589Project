package miem.projects.vulnerabilities.MINOR.FB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FB_DM_MONITOR_WAIT_ON_CONDITION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректная реализация ожидания условия
    public static class TaskProcessorIncorrect {
        private boolean conditionMet = false;
        private final Object lock = new Object();

        public void waitForCondition() throws InterruptedException {
            synchronized (lock) {
                if (!conditionMet) {
                    lock.wait();  // Ожидание без проверки в цикле
                }
            }
        }

        public void setCondition() {
            synchronized (lock) {
                conditionMet = true;
                lock.notifyAll();
            }
        }
    }

    // Корректная реализация с циклом проверки
    public static class TaskProcessorCorrect {
        private boolean conditionMet = false;
        private final Object lock = new Object();

        public void waitForCondition() throws InterruptedException {
            synchronized (lock) {
                while (!conditionMet) {  // Цикл while вместо if
                    lock.wait();
                }
            }
        }

        public void setCondition() {
            synchronized (lock) {
                conditionMet = true;
                lock.notifyAll();
            }
        }
    }

    // Лучшая практика с использованием java.util.concurrent
    public static class TaskProcessorBest {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private boolean conditionMet = false;

        public void waitForCondition() throws InterruptedException {
            lock.lock();
            try {
                while (!conditionMet) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }

        public void setCondition() {
            lock.lock();
            try {
                conditionMet = true;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void incorrectTest() {
        TaskProcessorIncorrect processor = new TaskProcessorIncorrect();
        
        new Thread(() -> {
            try {
                processor.waitForCondition();
                System.out.println("Incorrect: Condition met (potentially incorrect)");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void correctTest() {
        TaskProcessorCorrect processor = new TaskProcessorCorrect();
        
        new Thread(() -> {
            try {
                processor.waitForCondition();
                System.out.println("Correct: Condition properly met");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            processor.setCondition();
        }).start();
    }
}