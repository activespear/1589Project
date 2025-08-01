package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_EXIT {

    // Небезопасная конструкция: завершение программы сразу
    public static void unsafeExit(boolean error) {
        if (error) {
            System.exit(1);
        }
        System.out.println("No error, continuing execution.");
    }

    // Безопасная конструкция: выбрасывание исключения
    public static void safeThrow(boolean error) {
        if (error) {
            throw new RuntimeException("Ошибка при выполнении");
        }
        System.out.println("No error, continuing execution.");
    }

    public static void main(String[] args) {
        System.out.println("Запуск unsafeExit с error = false:");
        unsafeExit(false);

        System.out.println("Запуск unsafeExit с error = true (программа завершится):");
//      unsafeExit(true); // Закомментировано, чтобы программа не завершалась раньше времени

        System.out.println("Запуск safeThrow с error = false:");
        safeThrow(false);

        System.out.println("Запуск safeThrow с error = true:");
        try {
            safeThrow(true);
        } catch (RuntimeException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
    }
}

