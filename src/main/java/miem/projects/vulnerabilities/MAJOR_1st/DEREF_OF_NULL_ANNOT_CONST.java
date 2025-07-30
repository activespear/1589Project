package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_ANNOT_CONST {

    // Небезопасная версия
    static class UnsafeExample {
        public void test_helper(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Unsafe test_helper called with: " + s);
        }

        public void test1(int i) {
            String s = null;
            test_helper(s);
        }

        public void test2() {
            test_helper(null);
        }
    }

    // Безопасная версия
    static class SafeExample {
        public void test_helper(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Safe test_helper called with: " + s);
        }

        public void test1(int i) {
            String s = "default";
            test_helper(s);
        }

        public void test2() {
            test_helper("default");
        }
    }

    public static void runUnsafe() {
        UnsafeExample ue = new UnsafeExample();
        System.out.println("Running UnsafeExample.test1:");
        try {
            ue.test1(0);
            System.out.println("UnsafeExample.test1 completed");
        } catch (Exception e) {
            System.out.println("UnsafeExample.test1 threw: " + e);
        }

        System.out.println("Running UnsafeExample.test2:");
        try {
            ue.test2();
            System.out.println("UnsafeExample.test2 completed");
        } catch (Exception e) {
            System.out.println("UnsafeExample.test2 threw: " + e);
        }
    }

    public static void runSafe() {
        SafeExample se = new SafeExample();
        System.out.println("Running SafeExample.test1:");
        try {
            se.test1(0);
            System.out.println("SafeExample.test1 completed");
        } catch (Exception e) {
            System.out.println("SafeExample.test1 threw: " + e);
        }

        System.out.println("Running SafeExample.test2:");
        try {
            se.test2();
            System.out.println("SafeExample.test2 completed");
        } catch (Exception e) {
            System.out.println("SafeExample.test2 threw: " + e);
        }
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

