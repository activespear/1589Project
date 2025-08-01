package miem.projects.vulnerabilities.MAJOR_1st;

public class DIVISION_BY_ZERO_EX {

    // Небезопасная функция: может вызвать деление на 0
    public static void unsafeDivision(int input) {
        int x = 0;
        if (input < 10) {
            x = 0;
        }
        int result = 100 / x;  // потенциальное деление на 0
        System.out.println("Unsafe division result: " + result);
    }

    // Безопасная функция с проверкой деления на 0
    public static void safeDivision(int input) {
        int x = 0;
        if (input < 10) {
            x = 0;
        }
        if (x != 0) {
            int result = 100 / x;
            System.out.println("Safe division result: " + result);
        } else {
            System.out.println("Ошибка: деление на ноль пропущено");
        }
    }

    // Запуск небезопасной функции с обработкой исключения
    public static void runUnsafe(int input) {
        try {
            System.out.println("Запуск небезопасной функции:");
            unsafeDivision(input);
        } catch (ArithmeticException e) {
            System.out.println("Поймано исключение при небезопасном делении: " + e);
        }
    }

    // Запуск безопасной функции
    public static void runSafe(int input) {
        System.out.println("Запуск безопасной функции:");
        safeDivision(input);
    }

    public static void main(String[] args) {
        int input = 5;  // Пример входных данных, можно заменить

        runUnsafe(input);
        System.out.println();
        runSafe(input);
    }
}
