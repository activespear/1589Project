package miem.projects.vulnerabilities.MAJOR.FB;

public class BIT_AND_ZZ {

    // Небезопасная конструкция: выражение `(e & 0)` всегда будет 0 — бесполезное условие
    public static void unsafeBitAndCheck(int someValue) {
        int e = someValue;
        if ((e & 0) == 0) {
            System.out.println("Unsafe: This condition is always true (useless check).");
        }
    }

    // Безопасная конструкция: корректное условие, проверяющее, не равен ли e нулю
    public static void safeBitAndCheck(int someValue) {
        int e = someValue;
        if (e != 0) {
            System.out.println("Safe: e is not zero, proceeding with logic.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeBitAndCheck with value 42:");
        unsafeBitAndCheck(42);

        System.out.println("\nRunning safeBitAndCheck with value 42:");
        safeBitAndCheck(42);
    }
}

