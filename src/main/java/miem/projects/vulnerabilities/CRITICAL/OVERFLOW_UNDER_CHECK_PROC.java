package miem.projects.vulnerabilities.CRITICAL;

public class OVERFLOW_UNDER_CHECK_PROC {

    // Небезопасная версия: проверка в вызывающем методе, но без проверки в access
    public static void unsafeAccess(int index) {
        int[] array = new int[100];
        // Без проверки выхода за границы массива
        array[index] = 0;
        System.out.println("unsafeAccess: записано в array[" + index + "]");
    }

    public static void unsafeFunc(int index) {
        if (index < 200) {
            unsafeAccess(index);
        }
    }

    // Безопасная версия: проверка индекса внутри метода access
    public static void safeAccess(int index) {
        int[] array = new int[100];
        if (index >= 0 && index < array.length) {
            array[index] = 0;
            System.out.println("safeAccess: записано в array[" + index + "]");
        } else {
            System.out.println("safeAccess: индекс " + index + " вне диапазона, запись пропущена");
        }
    }

    public static void safeFunc(int index) {
        safeAccess(index);
    }

    public static void main(String[] args) {
        System.out.println("=== Запуск безопасной версии ===");
        safeFunc(50);
        safeFunc(150);  // вне диапазона, запись пропущена

        System.out.println("\n=== Запуск небезопасной версии ===");
        try {
            unsafeFunc(50);
            unsafeFunc(150);  // вызовет ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение в небезопасной версии: " + e);
        }
    }
}

