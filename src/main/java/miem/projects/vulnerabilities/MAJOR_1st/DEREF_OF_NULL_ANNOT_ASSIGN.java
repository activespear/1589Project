package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_ANNOT_ASSIGN {

    // Небезопасная версия
    static class UnsafeExample {
        private String str;

        public void test_helper(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Unsafe test_helper called with: " + s);
        }

        public void test(boolean cond) {
            String s = null;
            if (cond)
                s = "string";

            test_helper(s);
        }

        public UnsafeExample(boolean cond) {
            if (cond)
                str = "string";

            test_helper(str);
        }
    }

    // Безопасная версия
    static class SafeExample {
        private String str = "default";

        public void test_helper(String s /* @NotNull */) {
            if (s == null) {
                throw new NullPointerException("Argument 's' must not be null");
            }
            System.out.println("Safe test_helper called with: " + s);
        }

        public void test(boolean cond) {
            String s = cond ? "string" : "default";
            test_helper(s);
        }

        public SafeExample(boolean cond) {
            str = cond ? "string" : "default";
            test_helper(str);
        }
    }

    public static void runUnsafe() {
        System.out.println("Running UnsafeExample with cond=false:");
        try {
            UnsafeExample ue = new UnsafeExample(false);
            ue.test(false);
            System.out.println("UnsafeExample with cond=false completed");
        } catch (Exception e) {
            System.out.println("UnsafeExample with cond=false threw: " + e);
        }

        System.out.println("Running UnsafeExample with cond=true:");
        try {
            UnsafeExample ue = new UnsafeExample(true);
            ue.test(true);
            System.out.println("UnsafeExample with cond=true completed");
        } catch (Exception e) {
            System.out.println("UnsafeExample with cond=true threw: " + e);
        }
    }

    public static void runSafe() {
        System.out.println("Running SafeExample with cond=false:");
        try {
            SafeExample se = new SafeExample(false);
            se.test(false);
            System.out.println("SafeExample with cond=false completed");
        } catch (Exception e) {
            System.out.println("SafeExample with cond=false threw: " + e);
        }

        System.out.println("Running SafeExample with cond=true:");
        try {
            SafeExample se = new SafeExample(true);
            se.test(true);
            System.out.println("SafeExample with cond=true completed");
        } catch (Exception e) {
            System.out.println("SafeExample with cond=true threw: " + e);
        }
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

