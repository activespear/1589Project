package miem.projects.vulnerabilities.MINOR.FB;

public class NP_NULL_ON_SOME_PATH {
    // Некорректный пример
    public static void incorrectExample(String input) {
        if (input != null) {
            System.out.println("Длина строки: " + input.length());
        } else {
            // Потенциальный NPE, если выполнится эта ветвь
            System.out.println("Длина строки: " + input.length());
        }
    }

    // Корректный пример
    public static void correctExample(String input) {
        if (input != null) {
            System.out.println("Длина строки: " + input.length());
        } else {
            // Безопасная обработка null
            System.out.println("Ошибка: входная строка null");
        }
    }

    public static void main(String[] args) {
        incorrectExample(null);  // Вызовет NPE, если раскомментировать
        correctExample(null);    // Безопасный вывод
        correctExample("test");  // Корректная обработка
    }
}