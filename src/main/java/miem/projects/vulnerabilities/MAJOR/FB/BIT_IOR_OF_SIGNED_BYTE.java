package miem.projects.vulnerabilities.MAJOR.FB;

public class BIT_IOR_OF_SIGNED_BYTE {

    // Небезопасная конструкция: побитовая операция с отрицательным байтом
    public static void unsafeIOR() {
        byte b0 = (byte) 0xFF; // -1 в знаковом байте
        int x = 0;

        int result = (x << 8) | b0; // Ошибка: b0 расширяется до int со знаком (-1)
        System.out.println("Unsafe result: " + result); // -1
    }

    // Безопасная конструкция: побитовая операция с маской
    public static void safeIOR() {
        byte b0 = (byte) 0xFF; // -1 в знаковом байте
        int x = 0;

        int result = (x << 8) | (b0 & 0xFF); // Преобразование в беззнаковый 255
        System.out.println("Safe result: " + result); // 255
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeIOR:");
        unsafeIOR();

        System.out.println("\nRunning safeIOR:");
        safeIOR();
    }
}

