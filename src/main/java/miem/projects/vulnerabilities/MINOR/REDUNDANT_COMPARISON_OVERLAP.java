package miem.projects.vulnerabilities.MINOR;

public class REDUNDANT_COMPARISON_OVERLAP {
    public static void main(String[] args) {
        String user = "Alice";
        incorrectTest(user);
        correctTest(user);
    }

    // Потенциально небезопасное: сравнение переменной с самой собой
    public static void incorrectTest(String user) {
        if (user == user) {
            System.out.println("Это одно и то же!");
        }
    }

    // Корректная конструкция: убрано избыточное сравнение
    public static void correctTest(String user) {
        System.out.println("Используем значение переменной напрямую: " + user);
    }
}
