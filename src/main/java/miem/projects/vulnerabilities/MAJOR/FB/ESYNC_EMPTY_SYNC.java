package miem.projects.vulnerabilities.MAJOR.FB;

public class ESYNC_EMPTY_SYNC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private final Object lock = new Object();

            public void method() {
                synchronized (lock) {
                    // Пустой синхронизированный блок
                }
            }
        }
    }

    public static void correctTest() {
        class Test {
            private final Object lock = new Object();

            public void method() {
                // Синхронизация с реальной работой внутри
                synchronized (lock) {
                    System.out.println("Executing critical section");
                }
            }
        }
    }
}
