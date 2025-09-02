package miem.projects.vulnerabilities.MINOR;

public class UNUSED_FUNC_RES {
    public static void main(String[] args) {
        incorrectTest(3, 4);
        correctTest(3, 4);
    }

    public static void incorrectTest(int a, int b) {
        int sum = a + b;  // Результат сохранен, но не используется
        int result = a * b;
        System.out.println("Result: " + result);
    }

    public static void correctTest(int a, int b) {
        int result = a * b;  // sum не нужен
        System.out.println("Result: " + result);
    }
}
