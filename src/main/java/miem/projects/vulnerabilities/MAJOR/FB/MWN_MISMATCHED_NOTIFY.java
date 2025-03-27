package miem.projects.vulnerabilities.MAJOR.FB;

public class MWN_MISMATCHED_NOTIFY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private final Object lock = new Object();

            public void method() {
                // без захвата блокировки
                lock.notify();
            }
        }
        new Test().method();
    }

    public static void correctTest() {
        class Test {
            private final Object lock = new Object();

            public void method() {
                synchronized (lock) {
                    lock.notify();
                }
            }
        }
        new Test().method();
    }
}
