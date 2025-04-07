package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_COND {
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        int b = 1;
        incorrectTest(a, b);
        correctTest(a, b);
    }

    public static void incorrectTest(int a, int b) {
        if (a + b > a) {  // Здесь может произойти переполнение
            System.out.println("Condition passed, but overflow occurred.");
        } else {
            System.out.println("Condition failed, no overflow.");
        }
    }

    public static void correctTest(int a, int b) {
        if (a <= Integer.MAX_VALUE - b) {
            if (a + b > a) {
                System.out.println("Condition passed, no overflow.");
            }
        } else {
            System.out.println("Overflow detected, condition avoided.");
        }
    }
}