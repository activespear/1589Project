package miem.projects.vulnerabilities.MAJOR_1st;

public class CHECK_AFTER_PASS_TO_PROC {

    // Небезопасная реализация: результат используется до проверки
    public static void unsafe() {
        int result = someOperation();
        useResult(result); // ← потенциально небезопасно
        if (result < 0) {
            handleError();
        }
    }

    // Безопасная реализация: сначала проверка, потом использование
    public static void safe() {
        int result = someOperation();
        if (result < 0) {
            handleError();
            return;
        }
        useResult(result);
    }

    // Пример функции, возвращающей результат (может быть отрицательным)
    public static int someOperation() {
        return -1; // Пример ошибки
    }

    // Использование результата
    public static void useResult(int value) {
        System.out.println("Result: " + value);
    }

    // Обработка ошибки
    public static void handleError() {
        System.out.println("Ошибка: результат операции недопустим.");
    }

    public static void main(String[] args) {
        System.out.println("== Небезопасная реализация ==");
        unsafe();

        System.out.println("\n== Безопасная реализация ==");
        safe();
    }
}
