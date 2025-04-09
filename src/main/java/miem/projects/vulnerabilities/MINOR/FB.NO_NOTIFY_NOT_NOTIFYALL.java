package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NO_NOTIFY_NOT_NOTIFYALL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
        bestPracticeTest();
    }

    // Некорректная реализация с notify()
    public static class TaskProcessorIncorrect {
        private final Object lock = new Object();
        private boolean conditionMet = false;

        public void waitForCondition() throws InterruptedException {
            synchronized (lock) {
                while (!conditionMet) {
                    lock.wait();  // Ожидание условия
                }
            }
        }

        public void signalCondition() {
            synchronized (lock) {
                conditionMet = true;
                lock.notify();  // Риск: может разбудить не тот поток
            }
        }
    }

    // Корректная реализация с notifyAll()
    public static class TaskProcessorCorrect {
        private final Object lock = new Object();
        private boolean conditionMet = false;

        public void waitForCondition() throws InterruptedException {
            synchronized (lock) {
                while (!conditionMet) {
                    lock.wait();
                }
            }
        }

        public void signalCondition() {
            synchronized (lock) {
                conditionMet = true;
                lock.notifyAll();  // Все ожидающие потоки получат уведомление
            }
        }
    }

    // Лучшая практика с java.util.concurrent
    public static class TaskProcessorBest {
        private final java.util.concurrent.locks.Lock lock = 
            new java.util.concurrent.locks.ReentrantLock();
        private final java.util.concurrent.locks.Condition condition = 
            lock.newCondition();
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

        public void signalCondition() {
            lock.lock();
            try {
                conditionMet = true;
                condition.signalAll();  // Аналог notifyAll() в Condition
            } finally {
                lock.unlock();
            }
        }
    }

    public static void incorrectTest() {
        TaskProcessorIncorrect processor = new TaskProcessorIncorrect();
        
        // Создаем несколько ожидающих потоков
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    processor.waitForCondition();
                    System.out.println("Thread " + Thread.currentThread().getId() + 
                                      " awakened (may miss with notify())");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        
        // Сигнализируем - может разбудить только один поток
        new Thread(() -> {
            processor.signalCondition();
            System.out.println("Signaled with notify()");
        }).start();
    }

    public static void correctTest() {
        TaskProcessorCorrect processor = new TaskProcessorCorrect();
        
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    processor.waitForCondition();
                    System.out.println("Thread " + Thread.currentThread().getId() + 
                                      " correctly awakened");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        
        new Thread(() -> {
            processor.signalCondition();
            System.out.println("Signaled with notifyAll()");
        }).start();
    }

    public static void bestPracticeTest() {
        TaskProcessorBest processor = new TaskProcessorBest();
        
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    processor.waitForCondition();
                    System.out.println("Thread " + Thread.currentThread().getId() + 
                                      " awakened via Condition");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        
        new Thread(() -> {
            processor.signalCondition();
            System.out.println("Signaled via Condition");
        }).start();
    }
}