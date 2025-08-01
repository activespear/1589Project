package miem.projects.vulnerabilities.MAJOR.FB;

public class EC_NULL_ARG {

    // Небезопасная конструкция — вызов equals у потенциально null строки
    public static void unsafeEquals(String value) {
        if (value.equals("test")) {  // Может вызвать NullPointerException, если value == null
            System.out.println("Equal (unsafe)");
        } else {
            System.out.println("Not equal (unsafe)");
        }
    }

    // Безопасная конструкция — вызов equals у литерала, что безопасно при null
    public static void safeEquals(String value) {
        if ("test".equals(value)) {
            System.out.println("Equal (safe)");
        } else {
            System.out.println("Not equal (safe)");
        }
    }

    public static void main(String[] args) {
        String value = null;

        try {
            unsafeEquals(value);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException in unsafeEquals");
        }

        safeEquals(value);
    }
}
