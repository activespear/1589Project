package miem.projects.vulnerabilities.CRITICAL;

public class STATIC_OVERFLOW_LOCAL {

    // Небезопасная конструкция как функция
    public static void unsafeAccessBuf(int index) {
        int[] buf = new int[10];
        System.out.println("Небезопасный доступ с index = " + index);
        buf[index] = 7;  // Может вызвать ArrayIndexOutOfBoundsException
        System.out.println("Небезопасный доступ выполнен");
    }

    public static void unsafeRun() {
        int i = 10;
        if (i >= 10) {
            unsafeAccessBuf(i);
        }
    }

    // Безопасная конструкция как функция
    public static void safeAccessBuf(int index) {
        int[] buf = new int[10];
        System.out.println("Безопасный доступ с index = " + index);
        if (index >= 0 && index < buf.length) {
            buf[index] = 7;
            System.out.println("Безопасный доступ выполнен");
        } else {
            System.out.println("Индекс выходит за пределы массива.");
        }
    }

    public static void safeRun() {
        int i = 9;
        if (i >= 0 && i < 10) {
            safeAccessBuf(i);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Запуск небезопасной версии ===");
        try {
            unsafeRun();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение в небезопасной версии: " + e);
        }

        System.out.println("\n=== Запуск безопасной версии ===");
        safeRun();
    }
}

