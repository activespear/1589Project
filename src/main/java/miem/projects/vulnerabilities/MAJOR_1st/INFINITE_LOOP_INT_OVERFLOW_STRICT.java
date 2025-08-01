package miem.projects.vulnerabilities.MAJOR_1st;

public class INFINITE_LOOP_INT_OVERFLOW_STRICT {

    // Небезопасная конструкция: может привести к бесконечному циклу из-за переполнения int
    public static void unsafeLoop(byte[] buffer) {
        System.out.println("Запуск небезопасной конструкции:");
        for (int i = Integer.MAX_VALUE - 1; i <= Integer.MAX_VALUE; i++) {
            buffer[i % buffer.length] = 0;
            if (i < 0) {
                System.out.println("Переполнение произошло, выход из цикла");
                break;
            }
        }
    }

    // Безопасная конструкция: предсказуемое поведение, нет риска переполнения
    public static void safeLoop(byte[] buffer, int limit) {
        System.out.println("Запуск безопасной конструкции:");
        for (int i = 0; i < buffer.length && i < limit; i++) {
            buffer[i] = 0;
        }
    }

    public static void main(String[] args) {
        byte[] buffer = new byte[16];

        // Запуск небезопасного кода
        unsafeLoop(buffer);

        // Запуск безопасного кода
        safeLoop(buffer, 10);
    }
}
