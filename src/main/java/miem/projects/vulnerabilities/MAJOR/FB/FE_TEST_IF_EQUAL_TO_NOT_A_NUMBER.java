package miem.projects.vulnerabilities.MAJOR.FB;

public class FE_TEST_IF_EQUAL_TO_NOT_A_NUMBER {

    // Небезопасная проверка NaN через ==
    public static void unsafeCheck(double x) {
        if (x == Double.NaN) {
            System.out.println("Unsafe check: x is NaN");
        } else {
            System.out.println("Unsafe check: x is NOT NaN");
        }
    }

    // Безопасная проверка NaN через Double.isNaN()
    public static void safeCheck(double x) {
        if (Double.isNaN(x)) {
            System.out.println("Safe check: x is NaN");
        } else {
            System.out.println("Safe check: x is NOT NaN");
        }
    }

    public static void main(String[] args) {
        double x = Double.NaN;

        System.out.println("Running unsafe check:");
        unsafeCheck(x);

        System.out.println("Running safe check:");
        safeCheck(x);
    }
}

