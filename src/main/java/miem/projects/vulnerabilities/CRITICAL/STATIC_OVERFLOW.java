package miem.projects.vulnerabilities.CRITICAL;

public class STATIC_OVERFLOW {

    // Небезопасная версия — выход за границы массива
    public static void unsafeBufOverflow() {
        byte[] buf = new byte[1024];
        System.out.println("Запуск небезопасной версии");
        buf[1024] = 0; // ArrayIndexOutOfBoundsException
    }

    // Безопасная версия — проверка индекса перед записью
    public static void safeBufUse() {
        byte[] buf = new byte[1024];
        int index = 1023;
        System.out.println("Запуск безопасной версии");
        if (index >= 0 && index < buf.length) {
            buf[index] = 0;
            System.out.println("Безопасная запись в buf[" + index + "] выполнена");
        } else {
            System.out.println("Индекс вне границ массива, запись пропущена");
        }
    }

    public static void main(String[] args) {
        try {
            unsafeBufOverflow();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение в небезопасной версии: " + e);
        }

        safeBufUse();
    }
}
