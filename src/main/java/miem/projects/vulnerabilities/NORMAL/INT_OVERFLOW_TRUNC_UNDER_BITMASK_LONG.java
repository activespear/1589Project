package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_TRUNC_UNDER_BITMASK_LONG {
    public static void main(String[] args) {
        incorrectTest();
        correctTest(0x1FFFFFFFFL);
    }

    public static void incorrectTest() {
        long value = 0x1FFFFFFFFL;
        // Усечение long до 32 бит через маску
        int truncated = (int) (value & 0xFFFFFFFFL);

        // результат отрицательный (-1)
        System.out.println("Truncated: " + truncated);
    }

    public static void correctTest(long value) {
        if (value <= 0x7FFFFFFF) {
            int result = (int) value;
            System.out.println("Safe conversion: " + result);
        } else {
            System.out.println("Value too large to safely convert to int!");
        }
    }
}