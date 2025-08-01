package miem.projects.vulnerabilities.MAJOR.FB;

public class BIT_ADD_OF_SIGNED_BYTE {

    // Небезопасная конструкция: байт может быть интерпретирован как отрицательное число
    public static void unsafeBitAdd() {
        byte[] b = new byte[1];
        b[0] = (byte) 0xFF; // -1 в знаковом байте

        int x = 0;
        x = (x << 8) + b[0]; // Результат будет -1, не 255

        System.out.println("Unsafe result: " + x); // Печатает: -1
    }

    // Безопасная конструкция: байт маскируется, чтобы получить значение 0–255
    public static void safeBitAdd() {
        byte[] b = new byte[1];
        b[0] = (byte) 0xFF;

        int x = 0;
        x = (x << 8) + (b[0] & 0xFF); // Корректно: 255

        System.out.println("Safe result: " + x); // Печатает: 255
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeBitAdd:");
        unsafeBitAdd();

        System.out.println("\nRunning safeBitAdd:");
        safeBitAdd();
    }
}

