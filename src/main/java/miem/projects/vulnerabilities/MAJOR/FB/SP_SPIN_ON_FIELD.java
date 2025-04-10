package miem.projects.vulnerabilities.MAJOR.FB;

public class SP_SPIN_ON_FIELD {
    public static void main(String[] args) throws InterruptedException {
        correctTest();
        incorrectTest();
    }

    public static void incorrectTest() {
        class Test {
            private boolean flag = false;

            public void method() {
                // Спин-лок: цикл, который проверяет поле без синхронизации
                while (!flag) {
                    // Ожидаем изменения флага
                }
            }
        }
        Test test = new Test();
        test.method();
    }

    public static void correctTest() throws InterruptedException {
        class Test {
            private boolean flag = false;

            public void method() throws InterruptedException {
                synchronized (this) {
                    while (!flag) {
                        // Ожидаем, пока флаг не станет true
                        wait();
                    }
                }
            }

            public void setFlagTrue() {
                synchronized (this) {
                    flag = true;
                    // Уведомляем, что флаг изменился
                    notify();
                }
            }
        }

        Test test = new Test();
        Thread t = new Thread(() -> {
            try {
                test.method(); // Поток будет ожидать, пока флаг не станет true
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t.start();

        // Имитация изменения флага в другом потоке
        Thread.sleep(1000);
        test.setFlagTrue();
    }
}
