package miem.projects.vulnerabilities.MINOR.FB;

public class USER_BAD_KEYWORD_DEMO {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Демонстрация некорректного использования ключевых слов
    public static void incorrectTest() {
        int if = 10; // ОШИБКА: использование ключевого слова как идентификатора
        String try = "test"; // ОШИБКА: использование ключевого слова
        System.out.println(if + " " + try);
    }

    // Исправленная версия
    public static void correctTest() {
        int conditionValue = 10;
        String attempt = "test";
        System.out.println(conditionValue + " " + attempt);
    }
}