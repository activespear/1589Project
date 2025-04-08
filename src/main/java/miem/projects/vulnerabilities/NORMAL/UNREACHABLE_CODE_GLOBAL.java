package miem.projects.vulnerabilities.NORMAL;

public class UNREACHABLE_CODE_GLOBAL {
    // Глобальная переменная
    private static boolean flag = true;

    public static void main(String[] args) {
        incorrectTest();
        correctTest(false);
    }

    public static void incorrectTest() {
        if (flag) {
            System.out.println("Start test");
            return;
        }
        System.out.println("This will never be printed");
    }

    public static void correctTest(boolean change) {
        flag = change;

        if (flag) {
            System.out.println("Condition met, but flag is false now.");
        } else {
            System.out.println("This line is reachable because flag is false.");
        }
    }
}
