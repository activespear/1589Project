package miem.projects.vulnerabilities.NORMAL.FB;

public class MC_OVERRIDABLE_METHOD_CALL_IN_CONSTRUCTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Parent {
            public Parent() {
                init();
            }

            protected void init() {
                System.out.println("Parent init");
            }
        }

        class Child extends Parent {
            private final String data;

            public Child(String data) {
                this.data = data;
            }

            @Override
            protected void init() {
                System.out.println(data.length());
            }
        }

        new Child("test");
    }

    public static void correctTest() {
        class SafeParent {
            public SafeParent() {
                initInternal();
            }

            private void initInternal() {
                System.out.println("Internal init");
            }
        }

        new SafeParent();
    }
}
