package miem.projects.vulnerabilities.MAJOR.FB;

public class UWF_UNWRITTEN_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private String value;

            public Test() {
            }

            public void printValue() {
                // NullPointerException!
                System.out.println(value.toUpperCase());
            }
        }
    }

    public static void correctTest() {
        class Test {
            private String value;

            public Test(String value) {
                this.value = value;
            }

            public void printValue() {
                System.out.println(value.toUpperCase());
            }
        }
    }
}
