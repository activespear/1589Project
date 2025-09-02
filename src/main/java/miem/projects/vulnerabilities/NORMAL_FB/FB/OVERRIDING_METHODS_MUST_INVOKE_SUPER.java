package miem.projects.vulnerabilities.NORMAL.FB;

public class OVERRIDING_METHODS_MUST_INVOKE_SUPER {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Parent {
            protected void setup() { }
        }

        class Child extends Parent {
            @Override
            protected void setup() {
                // суперметод не вызывается
            }
        }

        Child child = new Child();
        child.setup();
    }

    public static void correctTest() {
        class Parent {
            protected void setup() { }
        }

        class Child extends Parent {
            @Override
            protected void setup() {
                super.setup(); // суперметод вызывается
            }
        }

        Child child = new Child();
        child.setup();
    }
}