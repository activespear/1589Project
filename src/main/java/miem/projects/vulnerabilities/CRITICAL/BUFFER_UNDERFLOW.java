package miem.projects.vulnerabilities.CRITICAL;

public class BUFFER_UNDERFLOW {

    // Небезопасные операции
    public static class UnsafeOperations {
        static int[] a = new int[1024];

        // Небезопасная обработка массива
        public static void unsafeProcess(int x) {
            System.out.println("[UNSAFE] Выполнение unsafeProcess (x = " + x + ")");
            int len, i, j;
            for (i = 0; i < 1024; i += 4) {
                len = x++ / 2;
                for (j = 0; j < len; j++) {
                    a[i + j] &= 0xfffffffe;  // Потенциальный выход за границы массива
                }
                a[i + (j - 1)] |= 1;  // Потенциальный выход за границы
            }
        }
    }

    // Безопасные операции
    public static class SafeOperations {
        static int[] a = new int[1024];

        // Безопасная обработка массива
        public static void safeProcess(int x) {
            System.out.println("[SAFE] Выполнение safeProcess (x = " + x + ")");
            int len, i, j;
            for (i = 0; i < 1024; i += 4) {
                len = x++ / 2;
                j = 0;
                // Безопасный цикл с проверкой границ
                for (; j < len && i + j < a.length; j++) {
                    a[i + j] &= 0xfffffffe;
                }
                // Безопасное обращение к последнему элементу
                if (j > 0 && i + j - 1 < a.length) {
                    a[i + (j - 1)] |= 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("BUFFER_UNDERFLOW DEMONSTRATION\n");

        // Тестирование безопасной версии
        System.out.println("=== ТЕСТ БЕЗОПАСНОЙ ВЕРСИИ ===");
        try {
            SafeOperations.safeProcess(10);
            System.out.println("Безопасная обработка завершена успешно");
        } catch (Exception e) {
            System.out.println("Ошибка в безопасной версии: " + e);
        }

        // Тестирование небезопасной версии
        System.out.println("\n=== ТЕСТ НЕБЕЗОПАСНОЙ ВЕРСИИ ===");
        try {
            UnsafeOperations.unsafeProcess(10);
            System.out.println("Небезопасная обработка завершена (возможны скрытые ошибки)");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Перехвачено исключение: " + e);
            System.out.println("Это ожидаемое поведение для небезопасной версии");
        }

        // Тест с критическими значениями
        System.out.println("\n=== ТЕСТ С КРИТИЧЕСКИМИ ЗНАЧЕНИЯМИ ===");
        try {
            System.out.println("Безопасная обработка (x = 2048):");
            SafeOperations.safeProcess(2048);
            System.out.println("Успешно завершено");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e);
        }

        try {
            System.out.println("\nНебезопасная обработка (x = 2048):");
            UnsafeOperations.unsafeProcess(2048);
            System.out.println("Завершено (но возможны ошибки)");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Перехвачено исключение: " + e);
        }

        // Проверка состояния массива
        System.out.println("\n=== ПРОВЕРКА СОСТОЯНИЯ МАССИВА ===");
        System.out.println("Длина массива: " + SafeOperations.a.length);
        System.out.println("Последний элемент безопасного массива: " +
                Integer.toHexString(SafeOperations.a[SafeOperations.a.length-1]));
        System.out.println("Последний элемент небезопасного массива: " +
                Integer.toHexString(UnsafeOperations.a[UnsafeOperations.a.length-1]));
    }
}