package miem.projects.vulnerabilities.CRITICAL;

public class FB_ML_SYNC_ON_UPDATED_FIELD {

    // Небезопасная реализация: объект блокировки может быть заменён
    static class UnsafeExample {
        private Object lock = new Object();

        public void unsafe() {
            synchronized (lock) {
                System.out.println("Небезопасный метод: синхронизация выполнена");
                try {
                    Thread.sleep(100); // имитация работы
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void updateLock() {
            lock = new Object();
            System.out.println("Небезопасный метод: объект lock обновлён");
        }
    }

    // Безопасная реализация: объект блокировки final, не может быть заменён
    static class SafeExample {
        private final Object lock = new Object();

        public void safe() {
            synchronized (lock) {
                System.out.println("Безопасный метод: синхронизация выполнена");
                try {
                    Thread.sleep(100); // имитация работы
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасная конструкция ===");
        SafeExample safe = new SafeExample();
        Thread safeThread1 = new Thread(safe::safe);
        Thread safeThread2 = new Thread(safe::safe);
        safeThread1.start();
        safeThread2.start();

        try {
            safeThread1.join();
            safeThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Потоки прерваны: " + e);
        }

        System.out.println("\n=== Небезопасная конструкция ===");
        UnsafeExample unsafe = new UnsafeExample();
        Thread unsafeThread1 = new Thread(unsafe::unsafe);
        Thread unsafeThread2 = new Thread(unsafe::updateLock);

        unsafeThread1.start();
        unsafeThread2.start();

        try {
            unsafeThread1.join();
            unsafeThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Потоки прерваны: " + e);
        }
    }
}

