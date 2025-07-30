package miem.projects.vulnerabilities.MAJOR_1st;

public class INFINITE_LOOP_INT_OVERFLOW_ARRAY {

    // Небезопасная конструкция: может привести к бесконечному циклу из-за переполнения int
    public static void unsafeLoop(byte[] buffer) {
        for (int i = Integer.MAX_VALUE - 1; i <= Integer.MAX_VALUE; i++) {
            buffer[i % buffer.length] = 0;
            // После Integer.MAX_VALUE, i станет Integer.MIN_VALUE и условие всегда будет выполняться
        }
    }

    // Безопасная конструкция: использует безопасный диапазон и ограничение
    public static void safeLoop(byte[] buffer, int limit) {
        for (int i = 0; i < buffer.length && i < limit; i++) {
            buffer[i] = 0;
        }
    }

    public static void main(String[] args) {
        byte[] buffer = new byte[10];

        System.out.println("Запуск безопасной конструкции:");
        safeLoop(buffer, 10);

        System.out.println("Попытка запуска небезопасной конструкции (будет ограничена вручную для демонстрации):");
        try {
            // Прерываем бесконечный цикл после нескольких итераций, чтобы избежать зависания
            Thread t = new Thread(() -> unsafeLoop(buffer));
            t.start();
            Thread.sleep(100); // Позволяем немного поработать
            t.stop(); // Не рекомендуется в продакшене, используется только для демонстрации
            System.out.println("Небезопасный цикл был остановлен вручную.");
        } catch (InterruptedException e) {
            System.out.println("Прерывание: " + e.getMessage());
        }
    }
}

