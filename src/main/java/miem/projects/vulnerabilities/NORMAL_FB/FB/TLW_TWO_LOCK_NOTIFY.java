package miem.projects.vulnerabilities.NORMAL.FB;

public class TLW_TWO_LOCK_NOTIFY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class TwoLockNotifier {
            private final Object lock1 = new Object();
            private final Object lock2 = new Object();

            public void problematicMethod() {
                synchronized (lock1) {
                    synchronized (lock2) {
                        // Потенциально небезопасно: notify под вложенной блокировкой
                        lock2.notifyAll();
                    }
                }
            }
        }

        TwoLockNotifier notifier = new TwoLockNotifier();
        notifier.problematicMethod();
        System.out.println("Executed problematicMethod");
    }

    public static void correctTest() {
        class SafeNotifier {
            private final Object singleLock = new Object();

            public void safeMethod() {
                synchronized (singleLock) {
                    // Вся логика под одной блокировкой
                    singleLock.notifyAll();
                }
            }
        }

        SafeNotifier notifier = new SafeNotifier();
        notifier.safeMethod();
        System.out.println("Executed safeMethod");
    }
}
