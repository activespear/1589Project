package miem.projects.vulnerabilities.MINOR.FB;

public class FB_VO_VOLATILE_INCREMENT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Counter {
            private volatile int count = 0;
            
            public void increment() {
                count++;  // Неатомарная операция с volatile
            }
        }
        
        Counter counter = new Counter();
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
        class Counter {
            private final java.util.concurrent.atomic.AtomicInteger count = 
                new java.util.concurrent.atomic.AtomicInteger(0);
            
            public void increment() {
                count.incrementAndGet();  // Атомарный инкремент
            }
        }
        
        Counter counter = new Counter();
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
        
        System.out.println("Correct count: " + counter.count.get());
    }
}