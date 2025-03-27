package miem.projects.vulnerabilities.MAJOR.FB;

public class NP_SYNC_AND_NULL_CHECK_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private Object lock;

            public void method() {
                // Проверка на null бессмысленна
                if (lock != null) {
                    synchronized (lock) {
                        System.out.println("Синхронизация на lock");
                    }
                }
            }
        }

        new Test().method();
    }

    public static void correctTest() {
        class Test {
            private final Object lock = new Object();

            public void method() {
                // Безопасная синхронизация
                synchronized (lock) {
                    System.out.println("Синхронизация на lock");
                }
            }
        }
        new Test().method();
    }
}