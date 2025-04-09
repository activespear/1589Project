package miem.projects.vulnerabilities.MINOR.FB;

public class FB_IS_INCONSISTENT_SYNC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Counter {
            private int count = 0;
            
            public synchronized void increment() {
                count++;
            }
            
            // Некорректно: несинхронизированный getter
            public int getCount() {
                return count;
            }
        }
        
        Counter counter = new Counter();
        System.out.println("Inconsistent counter: " + counter.getCount());
    }

    public static void correctTest() {
        class SafeCounter {
            private int count = 0;
            
            public synchronized void increment() {
                count++;
            }
            
            // Корректно: синхронизированный getter
            public synchronized int getCount() {
                return count;
            }
        }
        
        SafeCounter counter = new SafeCounter();
        System.out.println("Consistent counter: " + counter.getCount());
        
        // Альтернатива с volatile (если подходит для случая)
        class VolatileCounter {
            private volatile int count = 0;
            
            public void increment() {
                count++;  // Неатомарно, но изменения видны сразу
            }
            
            public int getCount() {
                return count;
            }
        }
    }
}