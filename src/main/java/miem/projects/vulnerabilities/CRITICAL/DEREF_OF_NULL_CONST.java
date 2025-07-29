package miem.projects.vulnerabilities.CRITICAL;

public class DEREF_OF_NULL_CONST {

    // Небезопасная конструкция: разыменование null
    public static void unsafeDereference() {
        Integer[] ptr = null;
        int x;

        // Это вызовет NullPointerException
        x = ptr[0];

        System.out.println("Значение x (unsafe): " + x);
    }

    // Безопасная конструкция с проверками
    public static void safeDereference() {
        Integer[] ptr = null;
        int x = 0;

        if (ptr != null && ptr.length > 0 && ptr[0] != null) {
            x = ptr[0];
        }

        System.out.println("Значение x (safe): " + x);
    }

    public static void main(String[] args) {
        System.out.println("Вызов безопасной конструкции:");
        safeDereference();

        System.out.println("\nВызов небезопасной конструкции:");
        try {
            unsafeDereference();
        } catch (NullPointerException e) {
            System.out.println("Произошло исключение: " + e);
        }
    }
}

