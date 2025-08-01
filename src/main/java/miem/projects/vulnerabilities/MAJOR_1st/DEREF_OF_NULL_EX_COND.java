package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_EX_COND {

    // Небезопасная версия
    static class UnsafeExample {
        static void use(int x) {
            System.out.println("use: " + x);
        }

        static void derefIf(int[] p, int x) {
            if (x > 1) {
                x -= p[0];
            }
            use(x);
        }

        static void example(int x) {
            // Здесь p == null, может возникнуть NullPointerException
            derefIf(null, x);
        }
    }

    // Безопасная версия
    static class SafeExample {
        static void use(int x) {
            System.out.println("use: " + x);
        }

        static void derefIf(int[] p, int x) {
            if (x > 1) {
                x -= p[0];
            }
            use(x);
        }

        static void exampleFixed(int x) {
            // p не null и x скорректирован так, чтобы не заходить в if с p == null
            derefIf(new int[] {0}, x > 1 ? 1 : x);
        }
    }

    public static void runUnsafe() {
        System.out.println("Running unsafe example with x = 2");
        try {
            UnsafeExample.example(2); // вызовет NPE
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e);
        }

        System.out.println("Running unsafe example with x = 1");
        UnsafeExample.example(1); // не вызовет NPE, т.к. x <= 1
    }

    public static void runSafe() {
        System.out.println("Running safe example with x = 2");
        SafeExample.exampleFixed(2);

        System.out.println("Running safe example with x = 1");
        SafeExample.exampleFixed(1);
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}
