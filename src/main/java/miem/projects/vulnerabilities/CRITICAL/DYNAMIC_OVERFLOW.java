package miem.projects.vulnerabilities.CRITICAL;

public class DYNAMIC_OVERFLOW {

    // Небезопасная конструкция: выход за пределы массива (IndexOutOfBoundsException)
    public static void unsafeBufferOverflow() {
        byte[] buf = new byte[1024];
        buf[1024] = 0; // Ошибка: допустимые индексы — 0..1023
    }

    // Безопасная конструкция: последний допустимый индекс
    public static void safeBufferAccess() {
        byte[] buf = new byte[1024];
        buf[1023] = 0; // OK
        System.out.println("Безопасная запись выполнена.");
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасная конструкция ===");
        safeBufferAccess();

        System.out.println("\n=== Небезопасная конструкция ===");
        try {
            unsafeBufferOverflow();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошло исключение: " + e);
        }
    }
}

