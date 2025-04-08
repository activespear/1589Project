package miem.projects.vulnerabilities.NORMAL;

public class UNREACHABLE_CODE_RET {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        System.out.println("Start test");
        if (true) {
            return;
        }
        System.out.println("This will never be printed");
    }

    public static void correctTest() {
        System.out.println("Start test");

        System.out.println("This line is reachable");
    }
}