package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_TRUNC_UNDER_BITMASK {
    public static void main(String[] args) {
        incorrectTest();
        correctTest(0x1FF);
    }

    public static void incorrectTest() {
        int largeValue = 0x1FF;  // 511, больше, чем 1 байт
        byte truncated = (byte) (largeValue & 0xFF);  // маска обрезает до младших 8 бит

        // это "wrap around" (выведет -1 (т.к. 0xFF == -1 в byte))
        System.out.println("Truncated value: " + truncated);  // Выведет -1 (т.к. 0xFF == -1 в byte)
    }

    public static void correctTest(int largeValue) {
        if (largeValue <= 0xFF) {
            byte result = (byte) (largeValue & 0xFF);
            System.out.println("Safe truncated value: " + result);
        } else {
            System.out.println("Value too large to safely truncate under bitmask!");
        }
    }
}