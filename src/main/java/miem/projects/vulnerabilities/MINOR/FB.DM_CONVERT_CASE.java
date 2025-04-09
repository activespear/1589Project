package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Locale;

public class DM_CONVERT_CASE {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Локаль-зависимое преобразование
        String input = "i";
        String upper = input.toUpperCase(); // В турецкой локале станет "İ" (U+0130)
        
        // 2. Ненадёжное сравнение
        if ("MAIL".equals(input.toUpperCase())) {  // Зависит от системной локали
            System.out.println("Match found");
        }

        // 3. Преобразование для хранения
        String normalized = "ß".toLowerCase(); // В немецкой локале станет "ss"
        System.out.println(normalized);
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Явное указание локали
        String input = "i";
        String upper = input.toUpperCase(Locale.ENGLISH); // Всегда "I"

        // 2. Надёжное сравнение
        if ("MAIL".equalsIgnoreCase(input)) {  // Или:
        // if ("MAIL".equals(input.toUpperCase(Locale.ROOT))) {
            System.out.println("Reliable match");
        }

        // 3. Контролируемая нормализация
        String normalized = "ß".toLowerCase(Locale.GERMAN); // Явное преобразование
        System.out.println("Normalized: " + normalized);
    }
}