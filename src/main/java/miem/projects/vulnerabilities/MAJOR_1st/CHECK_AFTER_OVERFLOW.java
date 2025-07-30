package miem.projects.vulnerabilities.MAJOR_1st;

public class CHECK_AFTER_OVERFLOW {

    // Небезопасная реализация: доступ к элементу массива до проверки границ
    public static void unsafeAccess(String[] args) {
        int[] arr = new int[10];
        int index = Integer.parseInt(args[0]); // предположим, args[0] существует и корректно
        int value = arr[index]; // ← возможное переполнение или исключение
        if (index < arr.length) {
            System.out.println(value);
        }
    }

    // Безопасная реализация: проверка границ до доступа
    public static void safeAccess(String[] args) {
        int[] arr = new int[10];
        int index = Integer.parseInt(args[0]);
        if (index >= 0 && index < arr.length) {
            int value = arr[index];
            System.out.println(value);
        } else {
            System.out.println("Индекс вне допустимого диапазона.");
        }
    }

    public static void main(String[] args) {
        System.out.println("== Небезопасный доступ ==");
        try {
            unsafeAccess(args);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e);
        }

        System.out.println("== Безопасный доступ ==");
        safeAccess(args);
    }
}
