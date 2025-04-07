package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_AFTER_CHECK {
    public static void main(String[] args) {
        int a = 1000000;
        int b = 3000;
        incorrectTest(a, b);
        correctTest(a, b);
    }

    public static void incorrectTest(int a, int b) {
        if (a < Integer.MAX_VALUE) {
            int result = a * b; // переполнение здесь
            System.out.println("Результат (переполнен): " + result);
        }
    }

    public static void correctTest(int a, int b) {
        long temp = (long) a * b;
        if (temp <= Integer.MAX_VALUE) {
            int result = (int) temp;
            System.out.println("Результат: " + result);
        } else {
            System.out.println("Переполнение при умножении!");
        }
    }
}