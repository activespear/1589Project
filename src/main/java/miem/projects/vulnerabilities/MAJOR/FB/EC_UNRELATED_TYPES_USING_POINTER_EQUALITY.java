package miem.projects.vulnerabilities.MAJOR.FB;

public class EC_UNRELATED_TYPES_USING_POINTER_EQUALITY {

    // Небезопасная конструкция: сравнение ссылок (==)
    public static void unsafeComparison() {
        String a = new String("test");
        Object b = new String("test");

        boolean result = a == b;  // Сравнение ссылок, что обычно неверно
        System.out.println("Unsafe comparison (a == b): " + result);
    }

    // Безопасная конструкция: сравнение содержимого через equals()
    public static void safeComparison() {
        String a = new String("test");
        Object b = new String("test");

        boolean result = a.equals(b);  // Правильное сравнение содержимого
        System.out.println("Safe comparison (a.equals(b)): " + result);
    }

    public static void main(String[] args) {
        unsafeComparison();
        safeComparison();
    }
}

