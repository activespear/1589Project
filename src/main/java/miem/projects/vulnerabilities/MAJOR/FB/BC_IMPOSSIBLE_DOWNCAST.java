package miem.projects.vulnerabilities.MAJOR.FB;

public class BC_IMPOSSIBLE_DOWNCAST {

    // Небезопасная конструкция: может привести к ClassCastException
    public static void unsafeDowncast() {
        Object value = "hello";
        try {
            Integer number = (Integer) value; // Ошибка: String не может быть приведён к Integer
            System.out.println("Number: " + number);
        } catch (ClassCastException e) {
            System.out.println("Unsafe downcast failed: " + e);
        }
    }

    // Безопасная конструкция: проверка instanceof перед приведением
    public static void safeDowncast() {
        Object value = "hello";
        if (value instanceof Integer) {
            Integer number = (Integer) value;
            System.out.println("Safe downcast succeeded: " + number);
        } else {
            System.out.println("Safe downcast avoided: value is not an Integer");
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeDowncast:");
        unsafeDowncast();

        System.out.println("\nRunning safeDowncast:");
        safeDowncast();
    }
}

