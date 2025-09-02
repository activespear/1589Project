package miem.projects.vulnerabilities.MINOR;

public class UNUSED_FUNC_RES_MINOR {
    public static void main(String[] args) {
        String text = "  Hello World  ";
        incorrectTest(text);
        correctTest(text);
    }

    public static void incorrectTest(String text) {
        text.trim();  // Результат вызова игнорируется
        System.out.println("Original text: '" + text + "'");
    }

    public static void correctTest(String text) {
        String trimmed = text.trim();  // Результат используется
        System.out.println("Trimmed text: '" + trimmed + "'");
    }
}
