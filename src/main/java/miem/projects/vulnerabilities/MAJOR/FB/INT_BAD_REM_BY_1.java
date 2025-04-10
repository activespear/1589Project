package miem.projects.vulnerabilities.MAJOR.FB;

public class INT_BAD_REM_BY_1 {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int x = 10;
        int y = x % 1; // x % 1 всегда 0

        System.out.println("Неправильный остаток от деления: " + y);
    }

    public static void correctTest() {
        int x = 10;
        int z = x % 2;

        System.out.println("Правильный остаток от деления: " + z);
    }
}