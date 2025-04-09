package miem.projects.vulnerabilities.MINOR.FB;

import javax.annotation.Nonnull;
import java.util.Objects;

public class TQ_UNKNOWN_VALUE_USED_WHERE_ALWAYS_STRICTLY_REQUIRED {

    // Константы для строгих проверок
    private static final String SECURE_DOMAIN = "trusted.com";
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1MB

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Непроверенный URL
        String userUrl = getUserInput();
        openSecureConnection(userUrl); // @UnknownValue в строгом контексте

        // 2. Невалидированный размер файла
        int fileSize = getUploadedFileSize();
        processFile(fileSize); // Возможное превышение лимита

        // 3. Непроверенный объект
        Object rawData = parseUserData();
        saveToSecureStorage(rawData); // Нарушение типовой безопасности
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Строгая валидация URL
        String userUrl = validateUrl(getUserInput());
        if (userUrl != null) {
            openSecureConnection(userUrl);
        }

        // 2. Контроль размера файла
        int fileSize = getUploadedFileSize();
        if (fileSize > 0 && fileSize <= MAX_FILE_SIZE) {
            processFile(fileSize);
        }

        // 3. Гарантированная типизация
        UserData data = safelyParseUserData();
        if (data != null && data.isValid()) {
            saveToSecureStorage(data);
        }
    }

    // Методы валидации
    private static String validateUrl(String url) {
        return url != null && url.endsWith(SECURE_DOMAIN) ? url : null;
    }

    private static UserData safelyParseUserData() {
        try {
            Object raw = parseUserData();
            return raw instanceof UserData ? (UserData) raw : null;
        } catch (Exception e) {
            return null;
        }
    }

    // Методы-заглушки
    private static String getUserInput() { return "http://untrusted.org"; }
    private static int getUploadedFileSize() { return 1024 * 1024 * 2; } // 2MB
    private static Object parseUserData() { return "malicious data"; }

    // Строгие методы (@AlwaysStrictlyRequired)
    private static void openSecureConnection(@Nonnull String verifiedUrl) {
        Objects.requireNonNull(verifiedUrl);
        if (!verifiedUrl.endsWith(SECURE_DOMAIN)) {
            throw new SecurityException("Untrusted domain");
        }
        // Открытие соединения
    }

    private static void processFile(int validatedSize) {
        if (validatedSize <= 0 || validatedSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Invalid file size");
        }
        // Обработка файла
    }

    private static void saveToSecureStorage(@Nonnull UserData data) {
        Objects.requireNonNull(data);
        // Сохранение данных
    }

    // Модель данных
    static class UserData {
        boolean isValid() { return false; }
    }
}