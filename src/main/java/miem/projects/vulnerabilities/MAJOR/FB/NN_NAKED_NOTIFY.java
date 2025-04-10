package miem.projects.vulnerabilities.MAJOR.FB;

public class NN_NAKED_NOTIFY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private final Object lock = new Object();

            public void method() {
                synchronized (lock) {
                    lock.notify();
                }
            }

            public void waitForCondition() throws InterruptedException {
                synchronized (lock) {
                    lock.wait();
                }
            }
        }
        Test test = new Test();

        new Thread(() -> {
            try {
                test.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}

        // Вызываем notify() без изменения состояния
        test.method();
    }

    public static void correctTest() {
        class Test {
            private final Object lock = new Object();
            private boolean condition = false;

            public void method() {
                synchronized (lock) {
                    // Изменяем состояние перед notify
                    condition = true;
                    lock.notify();
                }
            }

            public void waitForCondition() throws InterruptedException {
                synchronized (lock) {
                    while (!condition) {
                        lock.wait();
                    }
                    // ...
                }
            }
        }
        Test test = new Test();

        new Thread(() -> {
            try {
                test.waitForCondition();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}

        // Вызываем notify() уже с изменённым состоянием
        test.method();
    }
}
