package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_ASSIGN {

    // Небезопасная версия
    static class UnsafeExample {
        public void unsafeMethod(String str) {
            System.out.println(str.length());
        }
    }

    // Безопасная версия
    static class SafeExample {
        public void safeMethod(String str) {
            if (str != null) {
                System.out.println(str.length());
            } else {
                System.out.println("str is null");
            }
        }
    }

    public static void runUnsafe() {
        UnsafeExample ue = new UnsafeExample();
        System.out.println("Running unsafeMethod with non-null string:");
        ue.unsafeMethod("hello");

        System.out.println("Running unsafeMethod with null string:");
        try {
            ue.unsafeMethod(null);
            System.out.println("Completed without exception");
        } catch (NullPointerException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    public static void runSafe() {
        SafeExample se = new SafeExample();
        System.out.println("Running safeMethod with non-null string:");
        se.safeMethod("hello");

        System.out.println("Running safeMethod with null string:");
        se.safeMethod(null);
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe Example ===");
        runUnsafe();

        System.out.println("\n=== Safe Example ===");
        runSafe();
    }
}
