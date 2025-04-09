package miem.projects.vulnerabilities.MINOR.FB;

public class FB_TQ_EXPLICIT_UNKNOWN_SOURCE_VALUE_REACHES_ALWAYS_SINK {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: передача непроверенных данных в опасный метод
        String userInput = System.getenv("SENSITIVE_DATA");
        logSensitiveData(userInput);  // Всегда попадает в sink
    }

    public static void correctTest() {
        // Корректно: валидация перед передачей в sink
        String userInput = System.getenv("SENSITIVE_DATA");
        if (isSafeToLog(userInput)) {
            logSensitiveData(userInput);
        } else {
            System.out.println("Data not safe for logging");
        }
    }

    private static void logSensitiveData(String data) {
        // Опасный sink (например, лог-файл)
        System.out.println("Logged data: " + data);
    }

    private static boolean isSafeToLog(String data) {
        // Проверка на безопасность данных
        return data != null && !data.matches(".*[pP]assword.*");
    }
}