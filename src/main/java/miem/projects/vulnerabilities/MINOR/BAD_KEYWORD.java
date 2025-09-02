package miem.projects.vulnerabilities.MINOR;

public class BAD_KEYWORD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное
    public static void incorrectTest() {
        // int if = 10; // Недопустимый идентификатор, ключевое слово
    }

    // Корректная конструкция
    public static void correctTest() {
        int condition = 10;
        System.out.println(condition);
    }
}
