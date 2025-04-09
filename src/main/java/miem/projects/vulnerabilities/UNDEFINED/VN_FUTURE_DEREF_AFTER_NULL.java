package miem.projects.vulnerabilities.MINOR.FB;

public class VN_FUTURE_DEREF_AFTER_NULL {
    public static void main(String[] args) {
        String problematic = getPossiblyNull();
        incorrectTest(problematic);
        correctTest(problematic);
    }

    public static String getPossiblyNull() {
        return Math.random() > 0.5 ? "data" : null;
    }

    // Некорректная обработка потенциального null
    public static void incorrectTest(String data) {
        System.out.println("Длина строки: " + data.length()); // ОШИБКА: возможен NPE
    }

    // Корректная проверка на null
    public static void correctTest(String data) {
        if (data != null) {
            System.out.println("Длина строки: " + data.length());
        } else {
            System.out.println("Получен null, обработка невозможна");
        }
    }
}