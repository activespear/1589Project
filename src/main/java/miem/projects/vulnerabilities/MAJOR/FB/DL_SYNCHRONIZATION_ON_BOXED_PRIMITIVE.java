package miem.projects.vulnerabilities.MAJOR.FB;

public class DL_SYNCHRONIZATION_ON_BOXED_PRIMITIVE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            // автоупаковка примитива
            private final Integer lock = 42;

            public void method() {
                // синхронизация на объекте-обертке
                synchronized (lock) {
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
