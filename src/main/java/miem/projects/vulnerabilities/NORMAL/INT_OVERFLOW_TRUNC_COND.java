package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_TRUNC_COND {
    public static void main(String[] args) {
        incorrectTest(100);
        correctTest(100);
    }

    public static void incorrectTest(long a) {
        long bigValue = Integer.MAX_VALUE + a;
        // Приведение к int вызывает переполнение
        if ((int) bigValue > 100) {
            System.out.println("Truncated value is greater than 100");
        } else {
            System.out.println("Truncated value is NOT greater than 100");
        }
    }

    public static void correctTest(long a) {
        long bigValue = Integer.MAX_VALUE + a;
        // Используем переменную как есть, без усечения
        if (bigValue > 100) {
            System.out.println("Long value is greater than 100");
        } else {
            System.out.println("Long value is NOT greater than 100");
        }
    }
}