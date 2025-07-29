package miem.projects.vulnerabilities.CRITICAL;

public class FB_IL_INFINITE_RECURSIVE_LOOP {

    // Небезопасная конструкция: бесконечная рекурсия
    public static void unsafeCallMe() {
        unsafeCallMe(); // вызовет StackOverflowError
    }

    // Безопасная конструкция: ограниченная глубина рекурсии
    public static void safeCallMe(int depth) {
        if (depth <= 0) return;
        safeCallMe(depth - 1); // уменьшение глубины
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасная рекурсия ===");
        safeCallMe(10);
        System.out.println("Безопасная рекурсия завершена.");

        System.out.println("\n=== Небезопасная рекурсия ===");
        try {
            unsafeCallMe(); // вызовет StackOverflowError
        } catch (StackOverflowError e) {
            System.out.println("Произошло переполнение стека: " + e);
        }
    }
}
