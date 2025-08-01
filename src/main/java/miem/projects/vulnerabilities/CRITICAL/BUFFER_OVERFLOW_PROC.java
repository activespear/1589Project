package miem.projects.vulnerabilities.CRITICAL;

public class BUFFER_OVERFLOW_PROC {

    // Небезопасные операции
    public static class UnsafeProcedures {
        // Небезопасное заполнение массива (может вызвать переполнение)
        public static void unsafeFill(char[] p, int len) {
            System.out.println("[UNSAFE] Выполнение unsafeFill (len = " + len + ")");
            for (int i = 0; i < len; i++) {
                p[i] = 'a';  // Потенциальный выход за границы массива
            }
        }

        // Небезопасная операция переполнения
        public static void unsafeOverflow() {
            System.out.println("[UNSAFE] Запуск unsafeOverflow");
            char[] bufferInput = new char[50];
            unsafeFill(bufferInput, 1000);  // Явное переполнение
            unsafeFill(bufferInput, 30);    // Нормальный вызов
        }
    }

    // Безопасные операции
    public static class SafeProcedures {
        // Безопасное заполнение массива
        public static void safeFill(char[] p, int len) {
            System.out.println("[SAFE] Выполнение safeFill (len = " + len + ")");
            int limit = Math.min(len, p.length);  // Защита от переполнения
            for (int i = 0; i < limit; i++) {
                p[i] = 'a';
            }
            if (len > p.length) {
                System.out.println("  Предупреждение: запрошенная длина " + len +
                        " превышает размер массива " + p.length);
            }
        }

        // Безопасная операция
        public static void safeOverflow() {
            System.out.println("[SAFE] Запуск safeOverflow");
            char[] bufferInput = new char[50];
            safeFill(bufferInput, 1000);  // Безопасная обработка
            safeFill(bufferInput, 30);   // Нормальный вызов
        }
    }

    public static void main(String[] args) {
        System.out.println("BUFFER_OVERFLOW.PROC DEMONSTRATION\n");

        // Тест безопасных операций
        System.out.println("=== ТЕСТИРОВАНИЕ БЕЗОПАСНЫХ ОПЕРАЦИЙ ===");
        try {
            SafeProcedures.safeOverflow();
            System.out.println("Безопасные операции завершены успешно");
        } catch (Exception e) {
            System.out.println("Ошибка в безопасном режиме: " + e);
        }

        // Тест небезопасных операций
        System.out.println("\n=== ТЕСТИРОВАНИЕ НЕБЕЗОПАСНЫХ ОПЕРАЦИЙ ===");
        try {
            UnsafeProcedures.unsafeOverflow();
            System.out.println("Небезопасные операции завершены (но могли вызвать переполнение)");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Перехвачено исключение: " + e);
            System.out.println("Это ожидаемое поведение для небезопасных операций");
        }

        // Дополнительный тест с контролем состояния массива
        System.out.println("\n=== ПРОВЕРКА СОСТОЯНИЯ МАССИВОВ ===");
        char[] testArray = new char[10];

        System.out.println("Безопасное заполнение (len=15):");
        SafeProcedures.safeFill(testArray, 15);
        System.out.println("  Размер массива: " + testArray.length);
        System.out.println("  Последний элемент: '" + testArray[testArray.length-1] + "'");

        try {
            System.out.println("\nНебезопасное заполнение (len=15):");
            UnsafeProcedures.unsafeFill(testArray, 15);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  Исключение: " + e);
        }
    }
}
