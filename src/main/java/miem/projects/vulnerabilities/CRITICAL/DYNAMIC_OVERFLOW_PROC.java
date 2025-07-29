package miem.projects.vulnerabilities.CRITICAL;

public class DYNAMIC_OVERFLOW_PROC {

    // Небезопасная функция: может вызвать ArrayIndexOutOfBoundsException
    public static void unsafeAccessBuf(int[] buf) {
        buf[10] = 0; // Ошибка, если buf.length <= 10
    }

    public static void unsafeTest() {
        int[] buff = new int[10]; // Индексы: 0–9
        unsafeAccessBuf(buff);    // Попытка записи в buf[10] → исключение
    }

    // Безопасная функция: проверка длины перед доступом
    public static void safeAccessBuf(int[] buf) {
        if (buf.length > 10) {
            buf[10] = 0;
        }
    }

    public static void safeTest() {
        int[] buff = new int[11]; // Индексы: 0–10
        safeAccessBuf(buff);      // Безопасный доступ
        System.out.println("Безопасный доступ выполнен.");
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасная конструкция ===");
        safeTest();

        System.out.println("\n=== Небезопасная конструкция ===");
        try {
            unsafeTest();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошло исключение: " + e);
        }
    }
}

