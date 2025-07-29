package miem.projects.vulnerabilities.CRITICAL;

public class TAINTED_PTR_MIGHT {

    private String env;
    private char[] buf = new char[100];

    // Небезопасная версия: просто копирует, не проверяя длину или null
    public void unsafeTest(boolean flag) {
        if (flag) {
            env = System.getenv("VAR3");
        } else {
            env = "NONE";
        }

        System.arraycopy(env.toCharArray(), 0, buf, 0, Math.min(env.length(), buf.length));
        System.out.println("unsafeTest completed, buf start: " + new String(buf, 0, Math.min(env.length(), buf.length)));
    }

    // Безопасная версия: проверяет env на null и длину перед копированием
    public void safeTest(boolean flag) {
        if (flag) {
            env = System.getenv("VAR3");
        } else {
            env = "NONE";
        }

        if (env != null && env.length() < buf.length) {
            System.arraycopy(env.toCharArray(), 0, buf, 0, env.length());
            System.out.println("safeTest completed, buf start: " + new String(buf, 0, env.length()));
        } else {
            throw new IllegalArgumentException("Input string is too large or null");
        }
    }

    public static void main(String[] args) {
        TAINTED_PTR_MIGHT example = new TAINTED_PTR_MIGHT();

        System.out.println("=== Запуск небезопасной версии ===");
        try {
            example.unsafeTest(true);
        } catch (Exception e) {
            System.err.println("Небезопасная версия выбросила исключение: " + e);
        }

        System.out.println("\n=== Запуск безопасной версии ===");
        try {
            example.safeTest(true);
        } catch (Exception e) {
            System.err.println("Безопасная версия выбросила исключение: " + e);
        }
    }
}

