package miem.projects.vulnerabilities.NORMAL;

public class NO_CATCH_LIBRARY {
    public static void main(String[] args) {
        incorrectExample(args);
        correctExample(args);
    }

    // ❌ Потенциально небезопасное: NumberFormatException не обрабатывается
    public static void incorrectExample(String[] args) {
        int port = 7100;
        for (int i = 0; i < args.length; ++i) {
            if ("-p".equals(args[i]) && i + 1 < args.length) {
                i++;
                port = Integer.parseInt(args[i]); // Может выбросить NumberFormatException
            }
        }
        System.out.println("Порт (небезопасно): " + port);
    }

    // ✅ Безопасное: NumberFormatException обрабатывается
    public static void correctExample(String[] args) {
        int port = 7100;
        for (int i = 0; i < args.length; ++i) {
            if ("-p".equals(args[i]) && i + 1 < args.length) {
                i++;
                try {
                    port = Integer.parseInt(args[i]);
                } catch (NumberFormatException e) {
                    System.err.println("Некорректный порт: " + args[i]);
                    port = 7100; // возврат к значению по умолчанию
                }
            }
        }
        System.out.println("Порт (безопасно): " + port);
    }
}