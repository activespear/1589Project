package miem.projects.vulnerabilities.MAJOR_1st;

public class NULL_AFTER_DEREF_MIGHT {

    static class UnsafeExample {
        static void unsafeMethod(String str) {
            System.out.println(str.length());
            if (str == null) {
                System.out.println("str is null");
            }
        }
    }

    static class SafeExample {
        static void safeMethod(String str) {
            if (str != null) {
                System.out.println(str.length());
            } else {
                System.out.println("str is null");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("UnsafeExample with non-null:");
        UnsafeExample.unsafeMethod("hello");

        System.out.println("\nSafeExample with non-null:");
        SafeExample.safeMethod("hello");

        System.out.println("\nUnsafeExample with null:");
        try {
            UnsafeExample.unsafeMethod(null);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException in unsafeMethod");
        }

        System.out.println("\nSafeExample with null:");
        SafeExample.safeMethod(null);
    }
}

