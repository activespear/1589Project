package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_AFTER_NULL_EX_COND {

    // Небезопасная версия: вызов derefIfXIsPositive без повторной проверки p на null
    static class UnsafeExample {
        static void derefIfXIsPositive(int[] p, int x) {
            if (x > 0) {
                p[0] = x; // может вызвать NPE если p == null
            }
        }

        static void example(int[] p, int x) {
            if (p != null) {
                p[0] = 123;
            }
            derefIfXIsPositive(p, x); // нет проверки p на null перед вызовом
        }
    }

    // Безопасная версия: добавлена проверка p перед вызовом derefIfXIsPositive
    static class SafeExample {
        static void derefIfXIsPositive(int[] p, int x) {
            if (x > 0) {
                p[0] = x;
            }
        }

        static void exampleFixed(int[] p, int x) {
            if (p != null) {
                p[0] = 123;
            }
            if (p != null) {
                derefIfXIsPositive(p, x);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Running UnsafeExample with null array and x=5:");
        try {
            UnsafeExample.example(null, 5);
            System.out.println("No exception thrown in UnsafeExample");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught in UnsafeExample");
        }

        System.out.println("\nRunning SafeExample with null array and x=5:");
        try {
            SafeExample.exampleFixed(null, 5);
            System.out.println("No exception thrown in SafeExample");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught in SafeExample");
        }

        System.out.println("\nRunning SafeExample with non-null array and x=5:");
        int[] arr = new int[1];
        SafeExample.exampleFixed(arr, 5);
        System.out.println("SafeExample completed successfully");
    }
}
