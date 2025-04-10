package miem.projects.vulnerabilities.MAJOR.FB;

public class UUF_UNUSED_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            private void unusedMethod() {
                System.out.println("...");
            }

            public void doSomething() {
                System.out.println("...");
            }
        }

        MyClass myClass = new MyClass();
        myClass.doSomething();
    }

    public static void correctTest() {
        class MyClass {
            public void doSomething() {
                System.out.println("...");
            }
        }

        MyClass myClass = new MyClass();
        myClass.doSomething();
    }
}

