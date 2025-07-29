package miem.projects.vulnerabilities.CRITICAL;

public class TAINTED_INT_LOOP_MIGHT {

    // Небезопасная версия — без обработки исключений и проверок
    public void unsafeTest(boolean flag) {
        long size;

        if (flag) {
            String env = System.getenv("QQQ");
            System.out.println("Unsafe: QQQ = " + env);
            size = Long.parseLong(env);  // Может бросить NumberFormatException
        } else {
            size = 0;
        }

        System.out.println("Unsafe: запускаем цикл с size = " + size);
        for (int i = 0; i < size; i++) {
            // ... логика цикла
        }
        System.out.println("Unsafe: цикл завершён.");
    }

    // Безопасная версия — с проверками и обработкой исключений
    public void safeTest(boolean flag) {
        long size;

        if (flag) {
            String env = System.getenv("QQQ");
            System.out.println("Safe: QQQ = " + env);
            try {
                size = Long.parseLong(env);
                if (size < 0 || size > 1000) {
                    throw new IllegalArgumentException("Size is out of bounds: " + size);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid size format", e);
            }
        } else {
            size = 0;
        }

        System.out.println("Safe: запускаем цикл с size = " + size);
        for (int i = 0; i < size; i++) {
            // ... логика цикла
        }
        System.out.println("Safe: цикл завершён.");
    }

    public static void main(String[] args) {
        TAINTED_INT_LOOP_MIGHT example = new TAINTED_INT_LOOP_MIGHT();

        try {
            System.out.println("=== Запуск небезопасной версии с flag=true ===");
            example.unsafeTest(true);
        } catch (Exception e) {
            System.err.println("Поймано исключение в небезопасной версии: " + e);
        }

        System.out.println("\n=== Запуск безопасной версии с flag=true ===");
        try {
            example.safeTest(true);
        } catch (Exception e) {
            System.err.println("Поймано исключение в безопасной версии: " + e);
        }

        System.out.println("\n=== Запуск обеих версий с flag=false ===");
        example.unsafeTest(false);
        example.safeTest(false);
    }
}

