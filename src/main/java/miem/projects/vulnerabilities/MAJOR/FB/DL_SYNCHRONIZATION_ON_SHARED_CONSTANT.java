package miem.projects.vulnerabilities.MAJOR.FB;

public class DL_SYNCHRONIZATION_ON_SHARED_CONSTANT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            // строковый литерал интернируется
            private static final String LOCK = "LOCK";

            public void method() {
                // возможное разделение блокировки между разными классами
                synchronized (LOCK) {
                    System.out.println("Executing critical section");
                }
            }
        }
    }

    public static void correctTest() {
        class Test {
            // независимый объект
            private final Object lock = new Object();

            public void method() {
                // Гарантированно корректная синхронизация
                synchronized (lock) {
                    System.out.println("Executing critical section");
                }
            }
        }
    }
}
