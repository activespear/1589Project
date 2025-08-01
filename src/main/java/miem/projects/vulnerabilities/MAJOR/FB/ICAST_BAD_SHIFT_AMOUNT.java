package miem.projects.vulnerabilities.MAJOR.FB;

public class ICAST_BAD_SHIFT_AMOUNT {

    // Небезопасная конструкция: сдвиг больше допустимого диапазона для int
    public static void runUnsafe() {
        int shiftAmount = 40;  // Превышает 31
        int value = 0b10101010;

        // Java использует только 5 младших битов (0–31) для int-сдвига,
        // но это не всегда очевидно — возможна ошибка
        int result = value << shiftAmount;

        System.out.println("Unsafe shift result: " + result);
    }

    // Безопасная конструкция: сдвиг ограничен диапазоном 0–31
    public static void runSafe() {
        int shiftAmount = 40;
        int value = 0b10101010;

        // Безопасное использование: берём остаток от 32
        int result = value << (shiftAmount % 32);

        System.out.println("Safe shift result: " + result);
    }

    public static void main(String[] args) {
        runUnsafe();
        runSafe();
    }
}

