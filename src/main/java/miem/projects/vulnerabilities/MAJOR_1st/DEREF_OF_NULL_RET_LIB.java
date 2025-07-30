package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_OF_NULL_RET_LIB {

    // Небезопасная версия
    static class UnsafeExample {
        static void example() {
            String s = System.getenv("RANDFILE");
            // Если s == null, вызов s.charAt(0) вызовет NullPointerException
            if (s.charAt(0) == '\0') {
                System.out.println("First char is null char");
            }
        }
    }

    // Безопасная версия
    static class SafeExample {
        static void possibleFix() {
            String s = System.getenv("RANDFILE");
            if (s != null && s.length() > 0 && s.charAt(0) == '\0') {
                System.out.println("First char is null char");
            } else {
                System.out.println("String is null or empty or first char not null char");
            }
        }
    }

    public static void runUnsafe() {
        System.out.println("Running unsafe example:");
        try {
            UnsafeExample.example();
            System.out.println("Unsafe example completed without exception");
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException in unsafe example: " + e);
        }
    }

    public static void runSafe() {
        System.out.println("Running safe example:");
        SafeExample.possibleFix();
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

