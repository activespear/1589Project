package miem.projects.vulnerabilities.MAJOR_1st;

public class NULL_AFTER_DEREF {

    static class UnsafeExample {
        void unsafeMethod(String str) {
            // Здесь возможен NullPointerException, если str == null
            System.out.println(str.length());
            if (str == null) {
                System.out.println("str is null");
            }
        }
    }

    static class SafeExample {
        void safeMethod(String str) {
            if (str != null) {
                System.out.println(str.length());
            } else {
                System.out.println("str is null");
            }
        }
    }

    public static void main(String[] args) {
        UnsafeExample unsafe = new UnsafeExample();
        SafeExample safe = new SafeExample();

        System.out.println("UnsafeExample with non-null:");
        unsafe.unsafeMethod("hello");

        System.out.println("\nSafeExample with non-null:");
        safe.safeMethod("hello");

        System.out.println("\nUnsafeExample with null:");
        try {
            unsafe.unsafeMethod(null);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException in unsafeMethod");
        }

        System.out.println("\nSafeExample with null:");
        safe.safeMethod(null);
    }
}

