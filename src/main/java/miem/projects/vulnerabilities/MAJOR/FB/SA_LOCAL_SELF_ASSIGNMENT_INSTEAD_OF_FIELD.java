package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_LOCAL_SELF_ASSIGNMENT_INSTEAD_OF_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class A {
            private int x;
            public void setX(int x) {
                x = x;
            }
        }
    }

    public static void correctTest() {
        class A {
            private int x;
            public void setX(int x) {
                this.x = x;
            }
        }
    }
}