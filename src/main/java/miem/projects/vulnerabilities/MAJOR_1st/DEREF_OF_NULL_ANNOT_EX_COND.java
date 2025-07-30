package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_ANNOT_EX_COND {

    // Небезопасная версия
    static class UnsafeExample {
        public void func(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Unsafe func called with: " + s);
        }

        void passIfTrue(String str, int f1) {
            if (f1 > 0)
                func(str);
        }

        void test(int f1, int f2) {
            String str = null;
            passIfTrue(str, f1);
        }
    }

    // Безопасная версия
    static class SafeExample {
        public void func(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Safe func called with: " + s);
        }

        void passIfTrue(String str, int f1) {
            if (f1 > 0 && str != null)
                func(str);
        }

        void test(int f1, int f2) {
            String str = null;
            passIfTrue(str, f1);
        }
    }

    public static void runUnsafe() {
        UnsafeExample ue = new UnsafeExample();
        System.out.println("Running UnsafeExample.test with f1=1:");
        try {
            ue.test(1, 0);
            System.out.println("Completed without exception");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running UnsafeExample.test with f1=0:");
        try {
            ue.test(0, 0);
            System.out.println("Completed without exception");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void runSafe() {
        SafeExample se = new SafeExample();
        System.out.println("Running SafeExample.test with f1=1:");
        try {
            se.test(1, 0);
            System.out.println("Completed without exception");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running SafeExample.test with f1=0:");
        try {
            se.test(0, 0);
            System.out.println("Completed without exception");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe Examples ===");
        runUnsafe();
        System.out.println("\n=== Safe Examples ===");
        runSafe();
    }
}

