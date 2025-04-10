package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_ZERO_WRAP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest(Integer.MAX_VALUE);
    }

    public static void incorrectTest() {
        int a = Integer.MAX_VALUE;
        // Переполнение и "заворачивание" значения
        int result = a + 1;
        System.out.println(result);
    }

    public static void correctTest(int a) {
        if (a == Integer.MAX_VALUE) {
            System.out.println("Overflow detected, cannot add 1!");
        } else {
            int result = a + 1;
            System.out.println(result);
        }
    }
}