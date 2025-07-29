package miem.projects.vulnerabilities.CRITICAL;

public class BUFFER_OVERFLOW_LOOP {

    // Небезопасная версия (может привести к выходу за границы массива)
    public static class UnsafeLoop {
        static char[] buf = new char[10];

        public static void example() {
            System.out.println("[UNSAFE] Running UnsafeLoop");
            char x = 0;
            // Опасный цикл - нет проверки границ массива
            while (buf[x] == ' ') {
                ++x;
            }
            System.out.println("UnsafeLoop завершен (но мог выйти за границы массива)");
        }
    }

    // Безопасная версия
    public static class SafeLoop {
        static char[] buf = new char[10];

        public static void example() {
            System.out.println("[SAFE] Running SafeLoop");
            char x = 0;
            // Безопасный цикл - проверка границ перед доступом
            while (x < buf.length && buf[x] == ' ') {
                ++x;
            }
            System.out.println("SafeLoop завершен корректно");
        }
    }

    public static void main(String[] args) {
        System.out.println("BUFFER_OVERFLOW.LOOP DEMONSTRATION\n");

        // Инициализация буферов пробелами (чтобы цикл работал)
        for (int i = 0; i < UnsafeLoop.buf.length; i++) {
            UnsafeLoop.buf[i] = ' ';
            SafeLoop.buf[i] = ' ';
        }

        // Тест безопасной версии
        System.out.println("--- Тестирование SafeLoop ---");
        SafeLoop.example();  // Работает корректно

        // Тест небезопасной версии
        System.out.println("\n--- Тестирование UnsafeLoop ---");
        try {
            UnsafeLoop.example();  // Выйдет за границы массива
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
            System.out.println("Это ожидаемое поведение для UnsafeLoop");
        }

        // Дополнительный тест с частично заполненным буфером
        System.out.println("\n--- Тест с частично заполненным буфером ---");
        UnsafeLoop.buf[5] = 'a';  // Прерываем последовательность пробелов
        SafeLoop.buf[5] = 'a';

        try {
            System.out.println("Безопасная версия:");
            SafeLoop.example();  // Остановится на 5-м элементе
        } catch (Exception e) {
            System.out.println("Ошибка: " + e);
        }

        try {
            System.out.println("\nНебезопасная версия:");
            UnsafeLoop.example();  // Может работать, но ненадежно
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
        }
    }
}
