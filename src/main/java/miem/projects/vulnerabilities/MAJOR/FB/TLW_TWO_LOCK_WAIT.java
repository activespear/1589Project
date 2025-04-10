package miem.projects.vulnerabilities.MAJOR.FB;

public class TLW_TWO_LOCK_WAIT {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        incorrectMethod();
        correctMethod();
    }

    public static void incorrectMethod() throws InterruptedException {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("Ожидание...");
                // освобождаем только lock1, но lock2 остается захваченной
                lock1.wait();
            }
        }
    }

    public static void correctMethod() throws InterruptedException {
        synchronized (lock1) {
            System.out.println("Освобождаем блокировки перед ожиданием...");
        }

        synchronized (lock2) {
            // захватываем, но без риска deadlock
            synchronized (lock1) {
                lock1.wait();
            }
        }
    }
}
