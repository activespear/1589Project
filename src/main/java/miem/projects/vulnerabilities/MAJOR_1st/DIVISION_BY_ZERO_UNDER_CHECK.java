package miem.projects.vulnerabilities.MAJOR_1st;

public class DIVISION_BY_ZERO_UNDER_CHECK {

    // Заглушка для получения входных данных
    public static int getInput() {
        return 0; // пример, можно менять
    }

    // Небезопасная функция: проверка x >= 0, но деление на 0 не обработано
    public static void unsafeDivision() {
        int x = getInput();
        if (x >= 0) {
            int result = 100 / x; // может вызвать ArithmeticException при x == 0
            System.out.println("Unsafe division result: " + result);
        }
    }

    // Безопасная функция: проверка x > 0
    public static void safeDivision() {
        int x = getInput();
        if (x > 0) {
            int result = 100 / x;
            System.out.println("Safe division result: " + result);
        } else {
            System.out.println("Деление на ноль или отрицательное число пропущено");
        }
    }

    // Метод запуска небезопасного варианта с обработкой исключения
    public static void runUnsafe() {
        System.out.println("Запуск небезопасной функции:");
        try {
            unsafeDivision();
        } catch (ArithmeticException e) {
            System.out.println("Поймано исключение: " + e);
        }
    }

    // Метод запуска безопасного варианта
    public static void runSafe() {
        System.out.println("Запуск безопасной функции:");
        safeDivision();
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

