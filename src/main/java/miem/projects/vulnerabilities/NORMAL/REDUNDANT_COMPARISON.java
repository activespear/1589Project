package miem.projects.vulnerabilities.NORMAL;

public class REDUNDANT_COMPARISON {
    public static void main(String[] args) {
        incorrectTest(10);
        correctTest(10);
    }

    public static void incorrectTest(int b) {
        int a = 10;
        // Избыточное сравнение: b == 10, так как a == b
        if (a == b && b == 10) {
            System.out.println("a and b are both 10");
        }
    }

    public static void correctTest(int b) {
        int a = 10;

        if (a == b) {
            System.out.println("a and b are both 10");
        }
    }
}