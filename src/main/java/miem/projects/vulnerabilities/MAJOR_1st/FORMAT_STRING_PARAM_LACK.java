package miem.projects.vulnerabilities.MAJOR_1st;

public class FORMAT_STRING_PARAM_LACK {

    // Небезопасная конструкция: отсутствует аргумент для %d
    public static void unsafeFormat() {
        String name = "Alice";
        System.out.println("Небезопасное форматирование:");
        // Это вызовет MissingFormatArgumentException во время выполнения
        System.out.printf("Name: %s, Age: %d%n", name);
    }

    // Безопасная конструкция: все параметры переданы
    public static void safeFormat() {
        String name = "Alice";
        int age = 30;
        System.out.println("Безопасное форматирование:");
        System.out.printf("Name: %s, Age: %d%n", name, age);
    }

    public static void main(String[] args) {
        try {
            unsafeFormat();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e);
        }

        System.out.println();

        safeFormat();
    }
}
