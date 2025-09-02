package miem.projects.vulnerabilities.NORMAL;

public class NULL_AFTER_DEREF_RET {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное использование
    public static void incorrectTest() {
        String value = getValue().trim();
        if (value == null) {
            System.out.println("Value is null");
            return;
        }
        System.out.println(value);
    }

    // Корректная конструкция
    public static void correctTest() {
        String value = getValue().trim();
        System.out.println(value);
    }

    // Метод, который может вернуть null
    public static String getValue() {
        return Math.random() < 0.5 ? "Hello" : null;
    }
}
