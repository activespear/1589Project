package miem.projects.vulnerabilities.CRITICAL;

public class DYNAMIC_OVERFLOW_EX {

    // Небезопасная конструкция: возможен выход за границы массива
    public static void unsafeBufOverflow(int a) {
        byte[] buf;
        if (a > 0)
            buf = new byte[1024];
        else
            buf = new byte[512];

        if (a > 0)
            buf[1024] = 0; // Ошибка: индекс вне границ при a > 0
        else
            buf[511] = 0;  // OK
    }

    // Безопасная конструкция: проверка длины массива перед доступом
    public static void safeBufAccess(int a) {
        byte[] buf;
        if (a > 0)
            buf = new byte[1025];
        else
            buf = new byte[512];

        if (a > 0 && buf.length > 1024)
            buf[1024] = 0; // OK
        else if (a <= 0 && buf.length > 511)
            buf[511] = 0;  // OK

        System.out.println("Безопасная запись выполнена при a = " + a);
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасная конструкция ===");
        safeBufAccess(1);
        safeBufAccess(-1);

        System.out.println("\n=== Небезопасная конструкция ===");
        try {
            unsafeBufOverflow(1); // вызовет ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошло исключение при a = 1: " + e);
        }

        try {
            unsafeBufOverflow(-1); // сработает корректно
            System.out.println("Успешно при a = -1 (безопасный случай в небезопасной функции)");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошло исключение при a = -1: " + e);
        }
    }
}

