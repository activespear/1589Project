package miem.projects.vulnerabilities.MINOR.FB;

public class SF_SWITCH_NO_DEFAULT {
    public static void main(String[] args) {
        incorrectTest(5);
        correctTest(5);
    }

    // Некорректный switch без default
    public static void incorrectTest(int value) {
        switch (value) {
            case 1: System.out.println("One"); break;
            case 2: System.out.println("Two"); break;
            // ОШИБКА: отсутствует default case
        }
    }

    // Корректный switch с default
    public static void correctTest(int value) {
        switch (value) {
            case 1: System.out.println("One"); break;
            case 2: System.out.println("Two"); break;
            default: System.out.println("Unexpected value: " + value); // Защитная обработка
        }
    }
}