package miem.projects.vulnerabilities.MINOR.FB;

public class NP_NULL_ON_SOME_PATH_EXCEPTION {
    // Некорректный пример
    public static void incorrectExample(String input) {
        String data = null;
        try {
            data = parseInput(input);
            System.out.println("Данные: " + data.length());
        } catch (Exception e) {
            // Потенциальный NPE, если исключение возникло до инициализации data
            System.out.println("Ошибка обработки. Длина: " + data.length());
        }
    }

    // Корректный пример
    public static void correctExample(String input) {
        String data = null;
        try {
            data = parseInput(input);
            System.out.println("Данные: " + data.length());
        } catch (Exception e) {
            // Безопасная обработка
            System.out.println("Ошибка обработки" + 
                (data != null ? ". Длина: " + data.length() : ""));
        }
    }

    private static String parseInput(String input) throws Exception {
        if (input == null || input.isEmpty()) {
            throw new Exception("Неверный ввод");
        }
        return input.trim();
    }

    public static void main(String[] args) {
        incorrectExample(null);  // Вызовет NPE в блоке catch
        correctExample(null);    // Безопасная обработка
        correctExample("test");  // Нормальное выполнение
    }
}