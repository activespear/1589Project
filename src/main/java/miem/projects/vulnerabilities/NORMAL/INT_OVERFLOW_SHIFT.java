package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_SHIFT {
    public static void main(String[] args) {
        incorrectTest(Integer.MAX_VALUE);
        correctTest(Integer.MAX_VALUE);
    }

    public static void incorrectTest(int a) {
        int result = a << 1;
        System.out.println("Result: " + result);
    }

    public static void correctTest(int a) {
        if (a > Integer.MAX_VALUE >> 1) {
            System.out.println("Overflow would occur if shifted!");
        } else {
            int result = a << 1;
            System.out.println("Result: " + result);
        }
    }
}