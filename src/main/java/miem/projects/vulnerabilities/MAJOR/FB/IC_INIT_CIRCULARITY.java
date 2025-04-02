package miem.projects.vulnerabilities.MAJOR.FB;

public class IC_INIT_CIRCULARITY {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Пример с циклической зависимостью в статических инициализаторах
    public static void incorrectTest() {
        class A {
            static {
                System.out.println("Статический блок A");
                //B.getValue();
            }

            public static void getValue() {
                System.out.println("Метод A.getValue()");
            }
        }

        class B {
            static {
                System.out.println("Статический блок B");
                A.getValue();
            }

            public static void getValue() {
                System.out.println("Метод B.getValue()");
            }
        }

        new A(); // приведет к цикличности инициализации
    }

    public static void correctTest() {
        class A {
            static {
                System.out.println("Статический блок A");
            }

            public static void getValue() {
                System.out.println("Метод A.getValue()");
            }
        }

        class B {
            static {
                System.out.println("Статический блок B");
            }

            public static void getValue() {
                System.out.println("Метод B.getValue()");
            }
        }

        new A();
        new B();
    }
}