package miem.projects.vulnerabilities.MAJOR.FB;

public class UR_UNINIT_READ {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private int value;

            public Test(int value) {
                // this.value еще не инициализирован
                System.out.println(this.value);
                this.value = value;
            }
        }
    }

    public static void correctTest() {
        class Test {
            private int value;

            public Test(int value) {
                // параметр конструктора
                System.out.println(value);
                this.value = value;
            }
        }
    }
}
