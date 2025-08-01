package miem.projects.vulnerabilities.MAJOR.FB;

public class IMSE_DONT_CATCH_IMSE {

    // Небезопасный вызов wait() без синхронизации - выбросит IllegalMonitorStateException
    public static void unsafeWait() {
        Object lock = new Object();
        try {
            System.out.println("Unsafe wait started");
            lock.wait();  // Здесь будет IllegalMonitorStateException
            System.out.println("Unsafe wait finished");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        } catch (IllegalMonitorStateException e) {
            System.out.println("IllegalMonitorStateException caught: " + e);
        }
    }

    // Безопасный вызов wait() внутри synchronized-блока
    public static void safeWait() {
        Object lock = new Object();
        synchronized (lock) {
            try {
                System.out.println("Safe wait started");
                lock.wait(100);  // Ждём 100 мс для примера
                System.out.println("Safe wait finished");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeWait:");
        unsafeWait();

        System.out.println();

        System.out.println("Running safeWait:");
        safeWait();
    }
}

