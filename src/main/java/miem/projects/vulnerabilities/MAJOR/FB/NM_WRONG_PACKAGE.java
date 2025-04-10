package miem.projects.vulnerabilities.MAJOR.FB;

public class NM_WRONG_PACKAGE {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Parent {
            public void method(String str) {
                // ...
            }
        }

        class Child extends Parent {
            // @Override - error
            public void method(int n) {
                // ...
            }
        }
    }

    public static void correctTest() {
        class Parent {
            public void method(String str) {
                // ...
            }
        }

        class Child extends Parent {
            @Override
            public void method(String str) {
                // ...
            }
        }
    }
}
