package miem.projects.vulnerabilities.MAJOR.FB;

public class DL_SYNCHRONIZATION_ON_UNSHARED_BOXED_PRIMITIVE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            // значение переменной может быть уникальным для каждого экземпляра
            private Integer lock = 42;

            public void method() {
                // синхронизация на переменной Integer
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
