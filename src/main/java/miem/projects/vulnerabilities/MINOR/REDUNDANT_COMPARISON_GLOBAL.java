package miem.projects.vulnerabilities.MINOR;

public class REDUNDANT_COMPARISON_GLOBAL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное: сравнение boolean с true
    public static void incorrectTest() {
        final boolean DEBUG = false;
        if (DEBUG == true) {
            System.out.println("Сообщение DEBUG");
        }
    }

    // Корректная конструкция: просто используем boolean напрямую
    public static void correctTest() {
        final boolean DEBUG = false;
        if (DEBUG) {
            System.out.println("Сообщение DEBUG");
        }
    }
}
