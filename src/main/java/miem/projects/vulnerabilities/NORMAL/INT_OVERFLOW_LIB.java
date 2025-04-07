package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_LIB {
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        int b = 1;
        incorrectTest(a, b);
        correctTest(a, b);
    }
    public static void incorrectTest(int a, int b) {
        // Math.addExact выбрасывает исключение при переполнении
        int result = Math.addExact(a, b);
        System.out.println("Result: " + result);
    }

    public static void correctTest(int a, int b) {
        if (a > Integer.MAX_VALUE - b) {
            System.out.println("Overflow would occur!");
        } else {
            int result = a + b;
            System.out.println("Result: " + result);
        }
    }
}