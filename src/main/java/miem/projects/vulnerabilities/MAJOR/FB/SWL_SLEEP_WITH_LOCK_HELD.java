package miem.projects.vulnerabilities.MAJOR.FB;

public class SWL_SLEEP_WITH_LOCK_HELD {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws InterruptedException {
        synchronized (lock) {
            // ...
            Thread.sleep(5000);
        }
    }

    public static void correctTest() throws InterruptedException {
        synchronized (lock) {
            // ...
            lock.wait();
        }
    }
}
