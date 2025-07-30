package miem.projects.vulnerabilities.MAJOR_1st;

public class INFINITE_LOOP_STRING {

    // Небезопасная версия: не проверяет корректность входных данных
    public static void unsafeLoop(String input) {
        System.out.println("Запуск небезопасной версии с input = " + input);
        int iterations = Integer.parseInt(input);
        for (int i = 0; i < iterations; i++) {
            process();
        }
    }

    // Безопасная версия: проверяет входные данные, устанавливает разумные ограничения
    public static void safeLoop(String input) {
        System.out.println("Запуск безопасной версии с input = " + input);
        int iterations = 0;
        try {
            iterations = Integer.parseInt(input);
            if (iterations < 0 || iterations > 1000) {
                throw new IllegalArgumentException("Некорректное количество итераций");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный ввод, используем значение по умолчанию 10");
            iterations = 10;
        }
        for (int i = 0; i < iterations; i++) {
            process();
        }
    }

    // Заглушка для process()
    public static void process() {
        System.out.println("Processing...");
    }

    public static void main(String[] args) {
        // Пример запуска с разными входными значениями
        unsafeLoop("5");
        // unsafeLoop("abc"); // вызовет исключение NumberFormatException

        safeLoop("5");
        safeLoop("abc");
        safeLoop("2000");  // превысит лимит, будет использовано значение по умолчанию
    }
}

