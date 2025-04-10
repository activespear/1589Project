package miem.projects.vulnerabilities.MAJOR.FB;

public class LI_LAZY_INIT_UPDATE_STATIC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Example {
            // ...
            public void initialize() {
                // Дополнительная инициализация объекта
                System.out.println("Object initialized");
            }
        }

        class Test {
            private static Example instance;

            public static Example getInstance() {
                // Ленивая инициализация
                if (instance == null) {
                    instance = new Example();
                    // объект дополнительно обновляется после установки
                    instance.initialize();
                }
                return instance;
            }
        }
    }

    public static void correctTest() {
        class Example {
            // ...
            public void initialize() {
                // Дополнительная инициализация объекта
                System.out.println("Object initialized");
            }
        }

        class Test {
            private static volatile Example instance;

            public static Example getInstance() {
                // Ленивая инициализация
                if (instance == null) {
                    synchronized (Test.class) {
                        if (instance == null) {
                            instance = new Example();
                            instance.initialize(); // Дополнительная инициализация
                        }
                    }
                }
                return instance;
            }
        }
    }
}
