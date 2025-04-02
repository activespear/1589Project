package miem.projects.vulnerabilities.MAJOR.FB;

public class URF_UNREAD_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            private String unusedField = "Неиспользуемое поле";

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