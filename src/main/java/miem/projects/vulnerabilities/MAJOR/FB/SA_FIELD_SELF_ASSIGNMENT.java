package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_FIELD_SELF_ASSIGNMENT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class A {
            private int x;
            public void reset() {
                // поле остается неизменным
                x = x;
            }
        }
    }

    public static void correctTest() {
        class A {
            private int x;
            public void reset() {
                x = 10;
            }
        }
    }
}