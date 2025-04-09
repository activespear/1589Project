package miem.projects.vulnerabilities.MINOR.FB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FB_IS_FIELD_NOT_GUARDED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
        bestPracticeTest();
    }

    // Некорректная реализация - поле без защиты
    public static class CounterIncorrect {
        public int count = 0;  // Публичное поле без синхронизации
        
        public void increment() {
            count++;  // Небезопасно в многопоточной среде
        }
    }

    // Корректная реализация с synchronized
    public static class CounterCorrect {
        private int count = 0;
        private final Object lock = new Object();
        
        public void increment() {
            synchronized (lock) {
                count++;
            }
        }
        
        public int getCount() {
            synchronized (lock) {
                return count;
            }
        }
    }

    // Лучшая практика с java.util.concurrent
    public static class CounterBest {
        private final Lock lock = new ReentrantLock();
        private int count = 0;
        
        public void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
        
        public int getCount() {
            lock.lock();
            try {
                return count;
            } finally {
                lock.unlock();
            }
        }
    }

    // Альтернатива с AtomicInteger
    public static class CounterAtomic {
        private final java.util.concurrent.atomic.AtomicInteger count = 
            new java.util.concurrent.atomic.AtomicInteger(0);
        
        public void increment() {
            count.incrementAndGet();
        }
        
        public int getCount() {
            return count.get();
        }
    }

    public static void incorrectTest() {
        CounterIncorrect counter = new CounterIncorrect();
        
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };
        
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Incorrect count: " + counter.count);
    }

    public static void correctTest() {
        CounterCorrect counter = new CounterCorrect();
        
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };
        
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Correct count: " + counter.getCount());
    }

    public static void bestPracticeTest() {
        CounterAtomic counter = new CounterAtomic();
        
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };
        
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Atomic count: " + counter.getCount());
    }
}