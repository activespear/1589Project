package miem.projects.vulnerabilities.MAJOR.FB;

public class RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE {
    public static void main(String[] args) {
        incorrectTest("Hello");
        incorrectTest(null);
        correctTest("Hello");
    }

    public static void incorrectTest(String text) {
        if (text == null) {
            System.out.println("text равен null");
            return;
        }
        // text гарантированно не null
        System.out.println(text.length());

        if (text != null) { // Избыточная проверка
            System.out.println("Текст: " + text);
        }
    }

    public static void correctTest(String text) {
        if (text == null) {
            System.out.println("text равен null");
            return;
        }

        System.out.println("Текст: " + text);
    }
}

