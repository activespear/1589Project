package miem.projects.vulnerabilities.CRITICAL;

public class BUFFER_OVERFLOW_ARGV {

    // Небезопасная версия - может вызвать ArrayIndexOutOfBoundsException или StringIndexOutOfBoundsException
    public static void unsafeMethod(String[] args) {
        System.out.println("\nRunning UNSAFE method:");
        String input = args[0];
        char c = input.charAt(1000);  // Потенциальное исключение, если строка короче 1001 символа
        System.out.println("Character at index 1000: " + c);
    }

    // Безопасная версия с проверками
    public static void safeMethod(String[] args) {
        System.out.println("\nRunning SAFE method:");
        if (args.length == 0) {
            System.out.println("Ошибка: аргументы не предоставлены.");
            return;
        }

        String input = args[0];
        if (input.length() <= 1000) {
            System.out.println("Ошибка: строка слишком короткая (должна быть > 1000 символов).");
            return;
        }

        char c = input.charAt(1000);
        System.out.println("Character at index 1000: " + c);
    }

    public static void main(String[] args) {
        System.out.println("BUFFER_OVERFLOW DEMONSTRATION");

        try {
            // Запуск безопасного метода
            safeMethod(args);
        } catch (Exception e) {
            System.out.println("Safe method exception: " + e.getClass().getSimpleName());
        }

        try {
            // Запуск небезопасного метода
            unsafeMethod(args);
        } catch (Exception e) {
            System.out.println("Unsafe method exception: " + e.getClass().getSimpleName());
        }
    }
}