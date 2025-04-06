package miem.projects.vulnerabilities.NORMAL;

public class DEREF_OF_NULL_STRICT {
    public static void main(String[] args) {
        incorrectTest(Math.random() > 0.05 ? "hello" : null);
        correctTest(Math.random() > 0.05 ? "hello" : null);
    }

    public static void incorrectTest(String maybeNull) {
        // Разыменование без проверки на null
        System.out.println(maybeNull.length());
    }

    public static void correctTest(String maybeNull) {
        if (maybeNull != null) {
            // Безопасное разыменование
            System.out.println(maybeNull.length());
        } else {
            System.out.println("Значение отсутствует");
        }
    }
}