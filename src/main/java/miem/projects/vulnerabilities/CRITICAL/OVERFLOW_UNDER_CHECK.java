package miem.projects.vulnerabilities.CRITICAL;

public class OVERFLOW_UNDER_CHECK {

    static int[] buf = new int[10];

    // Небезопасная версия — нет корректных проверок индекса
    public static void unsafe(int i) {
        System.out.println("Небезопасный вызов с i = " + i);
        if (i < 20)
            buf[i] = 3;  // потенциальный выход за границы, если i < 0 или i >= 10

        if (i >= -1)
            buf[i] = 5;  // аналогично

        for (int j = 0; j < 100; ++j) {
            buf[j] = 7;  // выход за границы массива
        }
    }

    // Безопасная версия — проверка индексов
    public static void safe(int i) {
        System.out.println("Безопасный вызов с i = " + i);
        if (i >= 0 && i < buf.length)
            buf[i] = 3;

        if (i >= 0 && i < buf.length)
            buf[i] = 5;

        for (int j = 0; j < buf.length; ++j) {
            buf[j] = 7;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасный пример ===");
        safe(5);
        safe(-1);
        safe(15);

        System.out.println("\n=== Небезопасный пример ===");
        try {
            unsafe(5);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение при unsafe(5): " + e);
        }

        try {
            unsafe(-1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение при unsafe(-1): " + e);
        }

        try {
            unsafe(15);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение при unsafe(15): " + e);
        }
    }
}

