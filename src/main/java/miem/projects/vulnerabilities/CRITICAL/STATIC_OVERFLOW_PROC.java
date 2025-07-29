package miem.projects.vulnerabilities.CRITICAL;

public class STATIC_OVERFLOW_PROC {

    // Небезопасная версия: обращение к элементу с индексом 10 без проверки длины массива
    public static void unsafeAccessBuf(int[] buf) {
        System.out.println("Небезопасный доступ к buf[10]");
        buf[10] = 0; // Может вызвать ArrayIndexOutOfBoundsException
        System.out.println("Небезопасный доступ выполнен");
    }

    public static void unsafeRun() {
        int[] buf = new int[10]; // длина 10, индекс 10 — вне диапазона
        unsafeAccessBuf(buf);
    }

    // Безопасная версия: проверка длины массива перед доступом
    public static void safeAccessBuf(int[] buf) {
        System.out.println("Безопасный доступ к buf[10]");
        if (buf.length > 10) {
            buf[10] = 0;
            System.out.println("Безопасный доступ выполнен");
        } else {
            System.out.println("Длина массива недостаточна, доступ пропущен");
        }
    }

    public static void safeRun() {
        int[] buf = new int[11]; // длина 11, индекс 10 — в пределах
        safeAccessBuf(buf);
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

