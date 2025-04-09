package miem.projects.vulnerabilities.MINOR.FB;

public class VN_FUTURE_DEREF_OF_NULL {
    public static void main(String[] args) {
        incorrectTest(null);
        correctTest("Valid String");
    }

    // Потенциальное разыменование null
    public static void incorrectTest(String input) {
        if (input.length() > 0) { // Опасность: возможен NPE
            System.out.println("Длина строки: " + input.length());
        } else {
            System.out.println("Пустая строка");
        }
    }

    // Защищенная обработка null
    public static void correctTest(String input) {
        if (input != null && input.length() > 0) { // Безопасная проверка
            System.out.println("Длина строки: " + input.length());
        } else if (input != null) {
            System.out.println("Пустая строка");
        } else {
            System.out.println("Получен null");
        }
    }
}