package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_CONST {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        final int a = 1_000_000;
        final int b = 10_000;
        int result = a * b;
        System.out.println("Результат (переполнен): " + result);
    }

    public static void correctTest() {
        final int a = 1_000_000;
        final int b = 10_000;
        long result = (long) a * b;  // явно кастим один из операндов к long
        System.out.println("Результат: " + result);
    }
}