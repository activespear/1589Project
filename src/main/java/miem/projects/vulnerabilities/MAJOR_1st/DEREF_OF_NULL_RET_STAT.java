package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_RET_STAT {

    // Небезопасная версия
    static class UnsafeExample {
        static Integer getPtr(int id) {
            return null; // всегда возвращаем null
        }

        static void example() {
            Integer p1 = getPtr(1);
            if (p1 != null) p1 = 3;

            Integer p2 = getPtr(2);
            if (p2 != null) p2 = 1;

            Integer p3 = getPtr(3);
            if (p3 != null) p3 = 4;

            Integer p4 = getPtr(4);
            if (p4 != null) p4 = 1;

            Integer p5 = getPtr(5);
            // Здесь отсутствует проверка, что p5 != null, присваиваем сразу
            p5 = 5;

            Integer p6 = getPtr(6);
            if (p6 != null) p6 = 9;

            Integer p7 = getPtr(7);
            if (p7 != null) p7 = 2;

            Integer p8 = getPtr(8);
            if (p8 != null) p8 = 6;
        }
    }

    // Безопасная версия
    static class SafeExample {
        static Integer getPtr(int id) {
            return null; // всегда возвращаем null
        }

        static void example() {
            Integer p1 = getPtr(1);
            if (p1 != null) p1 = 3;

            Integer p2 = getPtr(2);
            if (p2 != null) p2 = 1;

            Integer p3 = getPtr(3);
            if (p3 != null) p3 = 4;

            Integer p4 = getPtr(4);
            if (p4 != null) p4 = 1;

            Integer p5 = getPtr(5);
            if (p5 != null) p5 = 5;

            Integer p6 = getPtr(6);
            if (p6 != null) p6 = 9;

            Integer p7 = getPtr(7);
            if (p7 != null) p7 = 2;

            Integer p8 = getPtr(8);
            if (p8 != null) p8 = 6;
        }
    }

    public static void runUnsafe() {
        System.out.println("Running unsafe example:");
        try {
            UnsafeExample.example();
            System.out.println("Unsafe example ran without exception (but might cause issues if p5 was null and used elsewhere)");
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException in unsafe example: " + e);
        }
    }

    public static void runSafe() {
        System.out.println("Running safe example:");
        SafeExample.example();
        System.out.println("Safe example completed without exception");
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

