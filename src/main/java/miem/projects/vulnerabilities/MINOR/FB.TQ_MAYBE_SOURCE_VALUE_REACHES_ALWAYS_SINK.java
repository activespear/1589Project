package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.List;

public class TQ_MAYBE_SOURCE_VALUE_REACHES_ALWAYS_SINK {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Передача непроверенных пользовательских данных
        String userInput = getUserInput(); // @MaybeSource
        processSensitiveData(userInput);   // @AlwaysSink

        // 2. Передача изменяемой коллекции
        List<String> mutableList = new ArrayList<>();
        mutableList.add("data");
        storeImmutableData(mutableList); // Потенциальная модификация

        // 3. Непроверенный URL
        String untrustedUrl = request.getParameter("url");
        openSecureConnection(untrustedUrl);
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Валидация входных данных
        String userInput = sanitizeInput(getUserInput());
        processSensitiveData(userInput);

        // 2. Защитное копирование
        List<String> mutableList = new ArrayList<>();
        mutableList.add("data");
        storeImmutableData(new ArrayList<>(mutableList));

        // 3. Проверка URL
        String untrustedUrl = validateUrl(request.getParameter("url"));
        openSecureConnection(untrustedUrl);
    }

    // Методы-заглушки
    private static String getUserInput() { return "user data"; }
    private static String sanitizeInput(String input) { return input.trim(); }
    private static String validateUrl(String url) { return url.startsWith("https://") ? url : null; }

    // @AlwaysSink методы
    private static void processSensitiveData(@Nonnull String data) {}
    private static void storeImmutableData(List<String> data) {}
    private static void openSecureConnection(String url) {}
}