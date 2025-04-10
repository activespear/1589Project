package miem.projects.vulnerabilities.MAJOR.FB;

public class ICAST_QUESTIONABLE_UNSIGNED_RIGHT_SHIFT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            void shiftExample(int value) {
                short result = (short) (value >>> 8);
                System.out.println("Incorrect result: " + result);
            }
        }

        MyClass myClass = new MyClass();
        myClass.shiftExample(-1024);
    }

    public static void correctTest() {
        class MyClass {
            void shiftExample(int value) {
                // явно обрезаем до 16 бит перед приведением
                short result = (short) ((value >>> 8) & 0xFFFF);
                System.out.println("Correct result: " + result);
            }
        }

        MyClass myClass = new MyClass();
        myClass.shiftExample(-1024);
    }
}