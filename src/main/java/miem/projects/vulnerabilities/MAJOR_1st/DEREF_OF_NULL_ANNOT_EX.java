package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_ANNOT_EX {

    // Небезопасная версия
    static class UnsafeExample {
        private int x;

        public UnsafeExample(int x) {
            this.x = x;
        }

        public void test_helper(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Unsafe test_helper called with: " + s);
        }

        public void test1(Object o) {
            String s = null;
            if (o instanceof String)
                s = (String) o;

            if (x > 0)
                test_helper(s);
        }

        public void test2(Object o, boolean b) {
            String s = null;
            if (o instanceof String)
                s = (String) o;
            else
                b = false;

            if (b)
                test_helper(s);
        }
    }

    // Безопасная версия
    static class SafeExample {
        private int x;

        public SafeExample(int x) {
            this.x = x;
        }

        public void test_helper(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Safe test_helper called with: " + s);
        }

        public void test1(Object o) {
            if (o instanceof String && x > 0) {
                String s = (String) o;
                test_helper(s);
            }
        }

        public void test2(Object o, boolean b) {
            if (o instanceof String && b) {
                String s = (String) o;
                test_helper(s);
            }
        }
    }

    public static void runUnsafe() {
        UnsafeExample ue = new UnsafeExample(1);
        System.out.println("Running UnsafeExample.test1 with valid String:");
        try {
            ue.test1("Hello");
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running UnsafeExample.test1 with non-String:");
        try {
            ue.test1(123);
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running UnsafeExample.test2 with valid String and true:");
        try {
            ue.test2("World", true);
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running UnsafeExample.test2 with non-String and true:");
        try {
            ue.test2(456, true);
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void runSafe() {
        SafeExample se = new SafeExample(1);
        System.out.println("Running SafeExample.test1 with valid String:");
        try {
            se.test1("Hello");
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running SafeExample.test1 with non-String:");
        try {
            se.test1(123);
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running SafeExample.test2 with valid String and true:");
        try {
            se.test2("World", true);
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("Running SafeExample.test2 with non-String and true:");
        try {
            se.test2(456, true);
            System.out.println("Completed");
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

