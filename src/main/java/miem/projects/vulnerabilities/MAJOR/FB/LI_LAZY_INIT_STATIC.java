package miem.projects.vulnerabilities.MAJOR.FB;

public class LI_LAZY_INIT_STATIC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Example {
            // ...
        }

        class Test {
            private static Example instance;

            public static Example getInstance() {
                // ленивая инициализация без синхронизации
                if (instance == null) {
                    instance = new Example();
                }
                return instance;
            }
        }
    }

    public static void correctTest() {
        class Example {
            // ...
        }

        class Test {
            // гарантируем правильное обновление состояния
            private static volatile Example instance;

            public static Example getInstance() {
                // двойная проверка внутри и снаружи блока
                // для минимизации количества блокировок
                if (instance == null) {
                    synchronized (Test.class) {
                        if (instance == null) {
                            instance = new Example();
                        }
                    }
                }
                return instance;
            }
        }
    }
}
