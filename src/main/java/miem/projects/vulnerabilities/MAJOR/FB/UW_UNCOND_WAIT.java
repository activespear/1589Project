package miem.projects.vulnerabilities.MAJOR.FB;

public class UW_UNCOND_WAIT {
    public static void main(String[] args) throws InterruptedException {
        correctTest();
        incorrectTest();
    }

    public static void incorrectTest() throws InterruptedException {
        class Test {
            private final Object lock = new Object();

            public void work() throws InterruptedException {
                synchronized (lock) {
                    // ожидание без условия
                    lock.wait();
                }
            }
        }
        Test test = new Test();
        test.work(); // Ошибочный вызов (может зависнуть)
    }

    public static void correctTest() throws InterruptedException {
        class Test {
            private final Object lock = new Object();
            private boolean ready = false;

            public void doWork() throws InterruptedException {
                synchronized (lock) {
                    // Проверка условия перед ожиданием
                    while (!ready) {
                        lock.wait();
                    }
                    // ...
                }
            }

            public void markReady() {
                synchronized (lock) {
                    ready = true;
                    lock.notifyAll();
                }
            }
        }

        Test test = new Test();

        Thread worker = new Thread(() -> {
            try {
                test.doWork();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        worker.start();
        Thread.sleep(1000);
        test.markReady(); // wait() корректно завершится

        worker.join();
    }
}