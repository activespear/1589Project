package miem.projects.vulnerabilities.MAJOR.FB;

public class MWN_MISMATCHED_WAIT {
    public static void main(String[] args) throws InterruptedException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws InterruptedException {
        class Test {
            private final Object lock = new Object();

            public void method() throws InterruptedException {
                // без захвата блокировки
                lock.wait();
            }
        }
        new Test().method();
    }

    public static void correctTest() throws InterruptedException {
        class Test {
            private final Object lock = new Object();

            public void method() throws InterruptedException {
                synchronized (lock) {
                    lock.wait();
                }
            }
        }
        new Test().method();
    }
}
