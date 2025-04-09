package miem.projects.vulnerabilities.MINOR.FB;

public class TQ_NEVER_VALUE_USED_WHERE_ALWAYS_REQUIRED {

    private static final int MAX_RETRIES = 3;
    private static final String DEFAULT_STATUS = "ACTIVE";

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Передача заведомо недопустимого значения
        processStatus("INVALID"); // @NeverValue в @AlwaysRequired контексте

        // 2. Использование null там, где требуется non-null
        String name = null;
        saveUserName(name); // @NeverValue (null) в @AlwaysRequired (non-null)

        // 3. Выход за границы допустимых значений
        int retries = -1; // Никогда не должно быть отрицательным
        performOperationWithRetry(retries);
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Проверка допустимых значений
        String status = getStatus();
        if (isValidStatus(status)) {
            processStatus(status);
        }

        // 2. Защита от null
        String name = getName();
        if (name != null) {
            saveUserName(name);
        }

        // 3. Валидация входных параметров
        int retries = getRetryCount();
        if (retries >= 0 && retries <= MAX_RETRIES) {
            performOperationWithRetry(retries);
        }
    }

    // Методы-заглушки
    private static String getStatus() { return "ACTIVE"; }
    private static String getName() { return "John Doe"; }
    private static int getRetryCount() { return 1; }
    private static boolean isValidStatus(String status) {
        return status != null && (status.equals("ACTIVE") || status.equals("INACTIVE"));
    }

    // @AlwaysRequired методы
    private static void processStatus(@Nonnull String status) {
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid status");
        }
        // Обработка статуса
    }

    private static void saveUserName(@Nonnull String name) {
        // Сохранение имени
    }

    private static void performOperationWithRetry(int retries) {
        if (retries < 0 || retries > MAX_RETRIES) {
            throw new IllegalArgumentException("Invalid retry count");
        }
        // Выполнение операции с повторами
    }
}