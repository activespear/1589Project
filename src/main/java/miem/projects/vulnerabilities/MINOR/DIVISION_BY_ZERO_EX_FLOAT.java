package miem.projects.vulnerabilities.NORMAL;

public class DIVISION_BY_ZERO_EX_FLOAT {
    public static void main(String[] args) {
        incorrectTest(10.0, 0.0);
        correctTest(10.0, 0.0);
    }

    // Потенциально небезопасное
    public static void incorrectTest(double dividend, double divisor) {
        // Может вернуть Infinity или NaN
        double result = dividend / divisor;
        System.out.println("Result: " + result);
    }

    // Корректная конструкция
    public static void correctTest(double dividend, double divisor) {
        if (Math.abs(divisor) < 1e-10) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        double result = dividend / divisor;
        System.out.println("Result: " + result);
    }
}
