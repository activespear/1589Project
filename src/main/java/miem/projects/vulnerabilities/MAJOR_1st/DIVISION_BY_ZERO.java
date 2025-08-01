package miem.projects.vulnerabilities.MAJOR_1st;

public class DIVISION_BY_ZERO {

    // Небезопасная функция: может бросить ArithmeticException при делении на 0
    public static void unsafeDivision(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = 100 / a;
        System.out.println("Result of unsafe division: " + b);
    }

    // Безопасная функция с проверкой деления на 0
    public static void safeDivision(String[] args) {
        int a = Integer.parseInt(args[0]);
        if (a != 0) {
            int b = 100 / a;
            System.out.println("Result of safe division: " + b);
        } else {
            System.out.println("Ошибка: деление на ноль невозможно");
        }
    }

    // Запуск небезопасной версии с обработкой исключения
    public static void runUnsafe(String[] args) {
        try {
            System.out.println("Запуск небезопасной функции:");
            unsafeDivision(args);
        } catch (ArithmeticException e) {
            System.out.println("Поймано исключение при небезопасном делении: " + e);
        }
    }

    // Запуск безопасной версии
    public static void runSafe(String[] args) {
        System.out.println("Запуск безопасной функции:");
        safeDivision(args);
    }

    public static void main(String[] args) {
        runUnsafe(args);
        System.out.println();
        runSafe(args);
    }
}

