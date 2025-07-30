package miem.projects.vulnerabilities.MAJOR_1st;

public class DYNAMIC_SIZE_MISMATCH {

    // Небезопасная реализация: может привести к ArrayIndexOutOfBoundsException
    public static void unsafeCopy(byte[] source, int length) {
        byte[] buffer = new byte[64];
        for (int i = 0; i < length; i++) {
            buffer[i] = source[i]; // Потенциальный выход за границы массива
        }
    }

    // Безопасная реализация: ограничивает количество копируемых байт
    public static void safeCopy(byte[] source, int length) {
        byte[] buffer = new byte[64];
        int safeLength = Math.min(length, buffer.length);
        for (int i = 0; i < safeLength; i++) {
            buffer[i] = source[i];
        }
    }

    // Метод запуска небезопасной функции
    public static void runUnsafe() {
        System.out.println("Запуск небезопасной копии:");
        byte[] data = new byte[128];
        unsafeCopy(data, 128); // Это приведет к исключению
    }

    // Метод запуска безопасной функции
    public static void runSafe() {
        System.out.println("Запуск безопасной копии:");
        byte[] data = new byte[128];
        safeCopy(data, 128);
    }

    public static void main(String[] args) {
        try {
            runUnsafe(); // Попробовать небезопасную реализацию
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
        }

        System.out.println();

        runSafe(); // Запустить безопасную реализацию
    }
}

