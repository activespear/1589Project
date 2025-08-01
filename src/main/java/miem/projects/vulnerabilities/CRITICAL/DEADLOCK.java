package miem.projects.vulnerabilities.CRITICAL;

public class DEADLOCK {

    // Небезопасные операции с возможностью взаимной блокировки
    public static class UnsafeLockOperations {
        private final Object lock = new Object();

        public synchronized void unsafeDirect() {
            System.out.println(Thread.currentThread().getName() + " acquired this lock (unsafeDirect)");
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            synchronized(lock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock object (unsafeDirect)");
                // Критическая секция
            }
        }

        public void unsafeReverse() {
            synchronized(lock) {
                System.out.println(Thread.currentThread().getName() + " acquired lock object (unsafeReverse)");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized(this) {
                    System.out.println(Thread.currentThread().getName() + " acquired this lock (unsafeReverse)");
                    // Критическая секция
                }
            }
        }
    }

    // Безопасные операции с фиксированным порядком блокировки
    public static class SafeLockOperations {
        private final Object lock = new Object();

        public void safeDirect() {
            synchronized(this) {
                System.out.println(Thread.currentThread().getName() + " acquired this lock (safeDirect)");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized(lock) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock object (safeDirect)");
                    // Критическая секция
                }
            }
        }

        public void safeReverse() {
            synchronized(this) {
                System.out.println(Thread.currentThread().getName() + " acquired this lock (safeReverse)");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized(lock) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock object (safeReverse)");
                    // Критическая секция
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("DEADLOCK DEMONSTRATION\n");

        // Создаем экземпляры для тестирования
        UnsafeLockOperations unsafeOps = new UnsafeLockOperations();
        SafeLockOperations safeOps = new SafeLockOperations();

        // Тест небезопасных операций (может привести к взаимной блокировке)
        System.out.println("=== TESTING UNSAFE OPERATIONS (POTENTIAL DEADLOCK) ===");
        Thread t1 = new Thread(() -> unsafeOps.unsafeDirect());
        Thread t2 = new Thread(() -> unsafeOps.unsafeReverse());

        t1.start();
        t2.start();

        try {
            // Даем потокам время на выполнение (или взаимоблокировку)
            Thread.sleep(2000);

            // Проверяем, живы ли потоки
            if (t1.isAlive() || t2.isAlive()) {
                System.out.println("\nDEADLOCK DETECTED! Threads are stuck:");
                System.out.println(" - " + t1.getName() + " state: " + t1.getState());
                System.out.println(" - " + t2.getName() + " state: " + t2.getState());
            } else {
                System.out.println("\nNo deadlock occurred (unlikely with this timing)");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Тест безопасных операций
        System.out.println("\n=== TESTING SAFE OPERATIONS ===");
        Thread t3 = new Thread(() -> safeOps.safeDirect());
        Thread t4 = new Thread(() -> safeOps.safeReverse());

        t3.start();
        t4.start();

        try {
            // Ожидаем завершения потоков
            t3.join();
            t4.join();
            System.out.println("Safe operations completed successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nDemonstration complete");
    }
}
