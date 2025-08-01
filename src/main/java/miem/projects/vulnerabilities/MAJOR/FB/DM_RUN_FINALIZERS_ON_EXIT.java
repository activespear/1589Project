package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_RUN_FINALIZERS_ON_EXIT {

    // Небезопасная конструкция: вызов устаревшего метода runFinalizersOnExit
    public static void unsafeRunFinalizers() {
        System.out.println("Вызов System.runFinalizersOnExit(true) — небезопасно и устарело.");
        @SuppressWarnings("deprecation")
        boolean previous = System.runFinalizersOnExit(true);
        System.out.println("Предыдущее состояние: " + previous);
    }

    // Безопасная конструкция: добавление shutdown hook
    public static void safeAddShutdownHook() {
        System.out.println("Добавление безопасного shutdown hook.");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook выполнен.");
            // Здесь можно очистить ресурсы
        }));
    }

    public static void main(String[] args) {
        System.out.println("Запуск unsafeRunFinalizers():");
        unsafeRunFinalizers();

        System.out.println("Запуск safeAddShutdownHook():");
        safeAddShutdownHook();

        System.out.println("Завершение main...");
    }
}

