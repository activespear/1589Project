package miem.projects.vulnerabilities.NORMAL;

public class UNREACHABLE_CODE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }
    public static void incorrectTest() {
        System.out.println("Start test");
        if (false) {
            // UNREACHABLE_CODE
            System.out.println("This line is unreachable");
        }
    }

    public static void correctTest() {
        System.out.println("Start test");
        if (true) {
            System.out.println("Condition met");
        }
        System.out.println("This line is reachable");
    }
}