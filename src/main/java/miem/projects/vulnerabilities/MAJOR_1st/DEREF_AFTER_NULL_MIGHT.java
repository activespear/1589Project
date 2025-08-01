package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_AFTER_NULL_MIGHT {

    static class UnsafeExample {
        static int getErrorLevel(int errorId) {
            return 0;
        }

        static void handleError(int errorId) {
            if (getErrorLevel(errorId) > 1) {
                System.exit(1);
            }
        }

        static void example(Integer[] p) {
            if (p == null) {
                handleError(313);
            }
            p[0] = -1; // может вызвать NullPointerException, если p == null
        }
    }

    static class SafeExample {
        static int getErrorLevel(int errorId) {
            return 0;
        }

        static void handleError(int errorId) {
            if (getErrorLevel(errorId) > 1) {
                System.exit(1);
            }
        }

        static void example(Integer[] p) {
            if (p == null) {
                handleError(313);
            } else {
                p[0] = -1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Running UnsafeExample with null array:");
        try {
            UnsafeExample.example(null);
            System.out.println("UnsafeExample: no exception");
        } catch (NullPointerException e) {
            System.out.println("UnsafeExample: NullPointerException caught");
        }

        System.out.println("\nRunning SafeExample with null array:");
        try {
            SafeExample.example(null);
            System.out.println("SafeExample: no exception");
        } catch (NullPointerException e) {
            System.out.println("SafeExample: NullPointerException caught");
        }

        System.out.println("\nRunning SafeExample with non-null array:");
        Integer[] arr = new Integer[1];
        SafeExample.example(arr);
        System.out.println("SafeExample completed successfully");
    }
}
