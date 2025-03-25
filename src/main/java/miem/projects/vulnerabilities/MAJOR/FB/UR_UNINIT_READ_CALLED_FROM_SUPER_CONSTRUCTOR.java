package miem.projects.vulnerabilities.MAJOR.FB;

public class UR_UNINIT_READ_CALLED_FROM_SUPER_CONSTRUCTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Parent {
            public Parent() {
                // вызов метода, который
                // использует неинициализированные поля
                init();
            }
            public void init() {
                //
            }
        }

        class Child extends Parent {
            private int value = 10;

            @Override
            public void init() {
                System.out.println(value);
            }
        }
    }

    public static void correctTest() {
        class Parent {
            public Parent() {
                // Убираем вызов init() из конструктора
            }
            public void init() {
                // ...
            }
        }

        class Child extends Parent {
            private int value = 10;

            public Child() {
                super();
                init();
            }

            public void init() {
                System.out.println(value);
            }
        }
    }
}
