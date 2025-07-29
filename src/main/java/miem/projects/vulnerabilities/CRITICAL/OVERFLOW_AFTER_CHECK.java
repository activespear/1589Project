package miem.projects.vulnerabilities.CRITICAL;

public class OVERFLOW_AFTER_CHECK {

    static class UnsafeExample {
        static byte[] buf = new byte[256];

        public static void overflow(int i) {
            if (i > 255)
                System.out.println("Небезопасный: i > 255");

            buf[i] = 0; // Возможен выход за пределы массива при i > 255 или i < 0
            System.out.println("Небезопасный: запись в buf[" + i + "] выполнена");
        }
    }

    static class SafeExample {
        static byte[] buf = new byte[256];

        public static void overflow(int i) {
            if (i >= 0 && i < buf.length) {
                buf[i] = 0;
                System.out.println("Безопасный: запись в buf[" + i + "] выполнена");
            } else {
                System.out.println("Безопасный: индекс вне диапазона, запись пропущена");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасная конструкция ===");
        SafeExample.overflow(100);
        SafeExample.overflow(256); // выход за границы, не будет записи

        System.out.println("\n=== Небезопасная конструкция ===");
        try {
            UnsafeExample.overflow(100);
            UnsafeExample.overflow(256); // вызовет ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение в небезопасной конструкции: " + e);
        }
    }
}

