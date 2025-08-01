package miem.projects.vulnerabilities.MAJOR_1st;

public class THROW_GENERIC_EXCEPTION {

    // Небезопасный метод: выбрасывает общий Exception
    public static void unsafeThrow() throws Exception {
        throw new Exception("Something failed");
    }

    // Безопасный метод: выбрасывает более конкретное исключение
    public static void safeThrow() {
        throw new IllegalArgumentException("Invalid argument");
    }

    public static void main(String[] args) {
        try {
            System.out.println("Calling unsafeThrow:");
            unsafeThrow();
        } catch (Exception e) {
            System.out.println("Caught exception from unsafeThrow: " + e);
        }

        try {
            System.out.println("\nCalling safeThrow:");
            safeThrow();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception from safeThrow: " + e);
        }
    }
}
