package miem.projects.vulnerabilities.MAJOR.FB;

public class UG_SYNC_SET_UNSYNC_GET {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private int value;

            public synchronized void setValue(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
        Test test = new Test();
        test.setValue(1);
        System.out.println(test.getValue());
    }

    public static void correctTest() {
        class Test {
            // Volatile гарантирует видимость изменений между потоками
            private volatile int value;

            public void setValue(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
        Test test = new Test();
        test.setValue(1);
        System.out.println(test.getValue());
    }
}
