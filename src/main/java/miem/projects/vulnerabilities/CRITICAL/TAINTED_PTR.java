package miem.projects.vulnerabilities.CRITICAL;

public class TAINTED_PTR {
    private String env;
    private char[] buf = new char[100];

    // Небезопасная версия — без проверки на null и длину
    public void unsafeTest() {
        env = System.getenv("VAR3");
        System.out.println("Unsafe: VAR3 = " + env);
        // Возможен NullPointerException или ArrayIndexOutOfBoundsException
        System.arraycopy(env.toCharArray(), 0, buf, 0, Math.min(env.length(), buf.length));
        System.out.println("Unsafe: Копирование выполнено");
    }

    // Безопасная версия — с проверкой на null и длину строки
    public void safeTest() {
        env = System.getenv("VAR3");
        System.out.println("Safe: VAR3 = " + env);
        if (env != null && env.length() < buf.length) {
            System.arraycopy(env.toCharArray(), 0, buf, 0, env.length());
            System.out.println("Safe: Копирование выполнено");
        } else {
            throw new IllegalArgumentException("Input string is too large or null");
        }
    }

    public static void main(String[] args) {
        TAINTED_PTR example = new TAINTED_PTR();

        try {
            System.out.println("=== Запуск небезопасной версии ===");
            example.unsafeTest();
        } catch (Exception e) {
            System.err.println("Небезопасная версия выбросила исключение: " + e);
        }

        try {
            System.out.println("\n=== Запуск безопасной версии ===");
            example.safeTest();
        } catch (Exception e) {
            System.err.println("Безопасная версия выбросила исключение: " + e);
        }
    }
}

