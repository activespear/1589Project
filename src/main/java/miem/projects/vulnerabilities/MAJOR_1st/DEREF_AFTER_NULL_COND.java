package miem.projects.vulnerabilities.MAJOR_1st;

public class DEREF_AFTER_NULL_COND {

    // Небезопасная версия: derefUnderSomeCondition вызывается без проверки на null
    static class UnsafeExample {
        static int getSomeData() {
            return (int)(Math.random() * 10) - 5;
        }

        static void derefUnderSomeCondition(int[] p) {
            if (getSomeData() > 0) {
                p[0] = -1; // потенциальный NPE если p == null
            }
        }

        static void example(int[] p) {
            if (p != null) {
                p[0] = 123;
            }
            derefUnderSomeCondition(p); // нет проверки p на null!
        }
    }

    // Безопасная версия: проверка на null перед вызовом derefUnderSomeCondition
    static class SafeExample {
        static int getSomeData() {
            return (int)(Math.random() * 10) - 5;
        }

        static void derefUnderSomeCondition(int[] p) {
            if (getSomeData() > 0) {
                p[0] = -1;
            }
        }

        static void exampleFixed(int[] p) {
            if (p != null) {
                p[0] = 123;
            }
            if (p != null) {
                derefUnderSomeCondition(p);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Running UnsafeExample with null array:");
        try {
            UnsafeExample.example(null);
            System.out.println("No exception thrown");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught in UnsafeExample");
        }

        System.out.println("\nRunning SafeExample with null array:");
        try {
            SafeExample.exampleFixed(null);
            System.out.println("No exception thrown");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught in SafeExample");
        }

        System.out.println("\nRunning SafeExample with non-null array:");
        int[] arr = new int[1];
        SafeExample.exampleFixed(arr);
        System.out.println("SafeExample completed successfully");
    }
}   
