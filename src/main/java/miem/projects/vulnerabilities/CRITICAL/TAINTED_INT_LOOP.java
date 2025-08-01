package miem.projects.vulnerabilities.CRITICAL;

public class TAINTED_INT_LOOP {

    // Небезопасная версия — без проверки значения size
    public void unsafeLoop() {
        String env = System.getenv("QQQ");
        System.out.println("Небезопасная версия, значение QQQ: " + env);
        long size = Long.parseLong(env);  // Может выбросить NumberFormatException
        System.out.println("Запуск цикла с size = " + size);
        for (int i = 0; i < size; i++) {
            // ... здесь может быть логика
        }
        System.out.println("Цикл завершён.");
    }

    // Безопасная версия — с проверкой диапазона и обработкой исключений
    public void safeLoop() {
        String env = System.getenv("QQQ");
        System.out.println("Безопасная версия, значение QQQ: " + env);
        long size;
        try {
            size = Long.parseLong(env);
            if (size <= 0 || size > 1000) {
                throw new IllegalArgumentException("Invalid size value: " + size);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format", e);
        }
        System.out.println("Запуск цикла с size = " + size);
        for (int i = 0; i < size; i++) {
            // ... здесь может быть логика
        }
        System.out.println("Цикл завершён.");
    }

    public static void main(String[] args) {
        TAINTED_INT_LOOP example = new TAINTED_INT_LOOP();

        try {
            System.out.println("=== Запуск небезопасной версии ===");
            example.unsafeLoop();
        } catch (Exception e) {
            System.err.println("Поймано исключение в небезопасной версии: " + e);
        }

        System.out.println("\n=== Запуск безопасной версии ===");
        try {
            example.safeLoop();
        } catch (Exception e) {
            System.err.println("Поймано исключение в безопасной версии: " + e);
        }
    }
}
