package miem.projects.vulnerabilities.MINOR.FB;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FB_JML_JSR166_CALLING_WAIT_RATHER_THAN_AWAIT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
        bestPracticeTest();
    }

    // Некорректная реализация с использованием wait() вместо await()
    public static class TaskProcessorIncorrect {
        private final Lock lock = new ReentrantLock();
        private final Object monitor = new Object();
        private boolean conditionMet = false;

        public void waitForCondition() throws InterruptedException {
            lock.lock();
            try {
                synchronized (monitor) {
                    if (!conditionMet) {
                        monitor.wait();  // Неправильно - используется wait() с Lock
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        public void signalCondition() {
            lock.lock();
            try {
                conditionMet = true;
                synchronized (monitor) {
                    monitor.notifyAll();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // Корректная реализация с использованием await()
    public static class TaskProcessorCorrect {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private boolean conditionMet = false;

        public void waitForCondition() throws InterruptedException {
            lock.lock();
            try {
                while (!conditionMet) {
                    condition.await();  // Правильное использование Condition
                }
            } finally {
                lock.unlock();
            }
        }

        public void signalCondition() {
            lock.lock();
            try {
                conditionMet = true;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // Лучшая практика с полным использованием java.util.concurrent
    public static class TaskProcessorBest {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        private boolean conditionMet = false;

        public void waitForCondition() throws InterruptedException {
            lock.lock();
            try {
                while (!conditionMet) {
                    condition.await();  // С правильной обработкой прерывания
                }
                // Действия после пробуждения
            } finally {
                lock.unlock();
            }
        }

        public void signalCondition() {
            lock.lock();
            try {
                conditionMet = true;
                condition.signal();  // Или signalAll() при необходимости
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
                System.out.println("Incorrect: Condition met (potentially wrong)");
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
            processor.signalCondition();
        }).start();
    }

    public static void bestPracticeTest() {
        TaskProcessorBest processor = new TaskProcessorBest();
        
        new Thread(() -> {
            try {
                processor.waitForCondition();
                System.out.println("Best practice: Condition handled correctly");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            processor.signalCondition();
        }).start();
    }
}