package miem.projects.vulnerabilities.CRITICAL;

public class OVERFLOW_AFTER_CHECK_EX {

    static class A {
        int[] x = new int[10];
        int y;
    }

    static A[] array = new A[10];

    // Небезопасная версия — выход за границы (i <= 10 при длине 10)
    public static void unsafeFunc(A[] q) {
        System.out.println("Запуск небезопасной функции");
        for (int i = 0; i <= 10; i++) {  // Ошибка: i = 10 выходит за пределы массива
            array[i] = q[i];
            System.out.println("array[" + i + "] присвоено");
        }
    }

    // Безопасная версия — ограничение по минимальной длине
    public static void safeFunc(A[] q) {
        System.out.println("Запуск безопасной функции");
        int limit = Math.min(array.length, q.length);
        for (int i = 0; i < limit; i++) {
            array[i] = q[i];
            System.out.println("array[" + i + "] присвоено");
        }
    }

    public static void main(String[] args) {
        A[] q = new A[11];
        for (int i = 0; i < q.length; i++) {
            q[i] = new A();
        }

        try {
            unsafeFunc(q);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение в небезопасной функции: " + e);
        }

        safeFunc(q);
    }
}
