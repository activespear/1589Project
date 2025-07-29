package miem.projects.vulnerabilities.CRITICAL;

public class TAINTED_INT_PTR {

    // Небезопасная версия — без проверки индекса
    public void unsafeSetBase(String base) {
        String env = System.getenv("QQQ");
        System.out.println("Unsafe: QQQ = " + env);
        int size = Integer.parseInt(env); // Может бросить NumberFormatException
        System.out.println("Unsafe: size = " + size);
        // Может вызвать StringIndexOutOfBoundsException
        char c = base.charAt(size);
        System.out.println("Unsafe: char at " + size + " = " + c);
    }

    // Безопасная версия — с проверкой индекса и выбросом исключения с сообщением
    public void safeSetBase(String base) {
        String env = System.getenv("QQQ");
        System.out.println("Safe: QQQ = " + env);
        int size = Integer.parseInt(env); // Может бросить NumberFormatException
        System.out.println("Safe: size = " + size);
        if (size >= 0 && size < base.length()) {
            char c = base.charAt(size);
            System.out.println("Safe: char at " + size + " = " + c);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + size);
        }
    }

    public static void main(String[] args) {
        TAINTED_INT_PTR example = new TAINTED_INT_PTR();
        String testString = "HelloWorld";

        try {
            System.out.println("=== Запуск небезопасной версии ===");
            example.unsafeSetBase(testString);
        } catch (Exception e) {
            System.err.println("Небезопасная версия выбросила исключение: " + e);
        }

        try {
            System.out.println("\n=== Запуск безопасной версии ===");
            example.safeSetBase(testString);
        } catch (Exception e) {
            System.err.println("Безопасная версия выбросила исключение: " + e);
        }
    }
}

