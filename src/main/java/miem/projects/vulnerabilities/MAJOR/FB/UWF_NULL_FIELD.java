package miem.projects.vulnerabilities.MAJOR.FB;

public class UWF_NULL_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private String value;

            public Test() {
                // value не инициализируется
            }
        }
    }

    public static void correctTest() {
        class Test {
            private String value;

            public Test(String value) {
                this.value = value;
            }
        }
    }
}
