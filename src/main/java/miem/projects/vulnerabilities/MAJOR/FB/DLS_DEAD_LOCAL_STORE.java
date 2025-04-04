package miem.projects.vulnerabilities.MAJOR.FB;

public class DLS_DEAD_LOCAL_STORE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            void doSomething() {
                // Значение переменной `x` нигде не используется
                int x = 10;
                x = 20;
            }
        }

        MyClass myClass = new MyClass();
        myClass.doSomething();
    }

    public static void correctTest() {
        class MyClass {
            void doSomething() {
                int x = computeValue(); // Значение переменной используется
                System.out.println("Computed value: " + x);
            }
            int computeValue() {
                return 42;
            }
        }
        MyClass myClass = new MyClass();
        myClass.doSomething();
    }
}