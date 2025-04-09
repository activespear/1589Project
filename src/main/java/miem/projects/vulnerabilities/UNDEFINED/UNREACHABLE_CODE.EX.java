package miem.projects.vulnerabilities.MINOR.FB;

public class UNREACHABLE_CODE_EX {
    public static void main(String[] args) {
        incorrectTest(true);
        correctTest(true);
    }

    // Пример недостижимого кода
    public static void incorrectTest(boolean condition) {
        if (condition) {
            System.out.println("Условие выполнено");
            return;
        } else {
            System.out.println("Этот код никогда не выполнится"); // ОШИБКА: недостижимый код
        }
        System.out.println("Этот код также недостижим");
    }

    // Корректная логика без недостижимого кода
    public static void correctTest(boolean condition) {
        if (condition) {
            System.out.println("Условие выполнено");
        } else {
            System.out.println("Условие не выполнено");
        }
        System.out.println("Этот код выполняется всегда");
    }
}