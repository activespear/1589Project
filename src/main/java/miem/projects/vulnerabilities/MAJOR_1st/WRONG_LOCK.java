package miem.projects.vulnerabilities.MAJOR_1st;

public class WRONG_LOCK {

    // Небезопасная версия — synchronized по строке, что может привести к неожиданным блокировкам
    static class UnsafeLock {
        private String lock = "LOCK";

        public void criticalSection() {
            synchronized (lock) {
                System.out.println("UnsafeLock: Inside critical section");
                // имитация работы
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Безопасная версия — synchronized по приватному объекту
    static class SafeLock {
        private final Object lock = new Object();

        public void criticalSection() {
            synchronized (lock) {
                System.out.println("SafeLock: Inside critical section");
                // имитация работы
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void runUnsafe() {
        UnsafeLock unsafeLock = new UnsafeLock();
        System.out.println("Running unsafe lock example:");
        unsafeLock.criticalSection();
    }

    public static void runSafe() {
        SafeLock safeLock = new SafeLock();
        System.out.println("Running safe lock example:");
        safeLock.criticalSection();
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

