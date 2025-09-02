package miem.projects.vulnerabilities.NORMAL;

public class NO_CATCH {
    public static void main(String[] args) {
        incorrectExample(args);
        correctExample(args);
    }

    // ❌ Потенциально небезопасное: исключения пробрасываются без обработки
    public static void incorrectExample(String[] args)
            throws ClassNotFoundException, LinkageError, ExceptionInInitializerError {

        if (args.length < 2) return;
        Class<?> clazz = getClassUnsafe(args[1]);
        System.out.println(clazz);
    }

    // ✅ Безопасное: исключения обрабатываются в блоке try-catch
    public static void correctExample(String[] args) {
        if (args.length < 2) return;
        try {
            Class<?> clazz = getClassSafe(args[1]);
            System.out.println(clazz);
        } catch (ClassNotFoundException | LinkageError | ExceptionInInitializerError e) {
            e.printStackTrace(); // корректная обработка или логирование
        }
    }

    // Вариант с пробросом исключений (небезопасно)
    public static Class<?> getClassUnsafe(String name)
            throws ClassNotFoundException, LinkageError, ExceptionInInitializerError {
        return Class.forName(name);
    }

    // Вариант без throws (исключения обрабатываются снаружи)
    public static Class<?> getClassSafe(String name)
            throws ClassNotFoundException, LinkageError, ExceptionInInitializerError {
        return Class.forName(name);
    }
}