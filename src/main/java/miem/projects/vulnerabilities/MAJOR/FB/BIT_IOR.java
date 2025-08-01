package miem.projects.vulnerabilities.MAJOR.FB;

public class BIT_IOR {

    // Небезопасная конструкция: сравнение после побитового OR с некорректным значением
    public static void unsafeOrCompare(int someValue) {
        int e = someValue;
        final int C = 0x0F;  // 00001111
        final int D = 0x10;  // 00010000

        if ((e | C) == D) {
            System.out.println("Unsafe: condition is likely always false or misleading.");
        }
    }

    // Безопасная конструкция: сравнение с допустимым ожидаемым значением
    public static void safeOrCompare(int someValue, int expectedValue) {
        int e = someValue;
        final int C = 0x0F;  // 00001111

        if ((e | C) == expectedValue) {
            System.out.println("Safe: valid comparison using OR mask.");
        }
    }

    public static void main(String[] args) {
        int testValue = 0x00;

        System.out.println("Running unsafeOrCompare:");
        unsafeOrCompare(testValue);

        System.out.println("\nRunning safeOrCompare:");
        int expected = 0x0F;
        safeOrCompare(testValue, expected);
    }
}

