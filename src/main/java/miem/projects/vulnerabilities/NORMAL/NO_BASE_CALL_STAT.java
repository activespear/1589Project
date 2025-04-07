package miem.projects.vulnerabilities.NORMAL;

public class NO_BASE_CALL_STAT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Base {
            void init() {
                System.out.println("Parent init");
            }
        }

        class Child extends Base {
            @Override
            void init() {
                // нет вызова super
                System.out.println("Child init");
            }
        }

        Base a = new Child();
        // базовый метод не вызван
        a.init();
    }

    public static void correctTest() {
        class Base {
            void init() {
                System.out.println("Parent init");
            }
        }

        class Child extends Base {
            @Override
            void init() {
                super.init();
                System.out.println("Child init");
            }
        }

        Base a = new Child();
        a.init();
    }
}
