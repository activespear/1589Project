package miem.projects.vulnerabilities.MAJOR.FB;

public class VA_FORMAT_STRING_USES_NEWLINE {

    // Небезопасная конструкция: использует \n в формате
    public static void unsafePrint(String errorMessage) {
        System.out.printf("Ошибка произошла: %s\n", errorMessage);
    }

    // Безопасная конструкция: использует %n для новой строки
    public static void safePrint(String errorMessage) {
        System.out.printf("Ошибка произошла: %s%n", errorMessage);
    }

    public static void main(String[] args) {
        String errorMessage = "Неверный ввод";

        System.out.println("=== Unsafe print ===");
        unsafePrint(errorMessage);

        System.out.println("=== Safe print ===");
        safePrint(errorMessage);
    }
}

