package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW {
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        int b = 1;
        incorrectTest(a, b);
        correctTest(a, b);
    }

    public static void incorrectTest(int a, int b) {
        // результат может стать отрицательным
        int result = a + b;
        System.out.println("Результат (переполнен): " + result);
    }

    public static void correctTest(int a, int b) {
        if ((long) a + b > Integer.MAX_VALUE) {
            System.out.println("Переполнение при сложении!");
        } else {
            int result = a + b;
            System.out.println("Результат: " + result);
        }
    }
}