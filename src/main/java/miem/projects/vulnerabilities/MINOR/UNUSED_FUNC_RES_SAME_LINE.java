package miem.projects.vulnerabilities.MINOR;

public class UNUSED_FUNC_RES_SAME_LINE {
    public static void main(String[] args) {
        String text = "  Example text  ";
        incorrectTest(text);
        correctTest(text);
    }

    public static void incorrectTest(String text) {
        text.trim();  // Результат вызова игнорируется
        System.out.println("Original text: '" + text + "'");
    }

    public static void correctTest(String text) {
        text = text.trim();  // Результат используется
        System.out.println("Trimmed text: '" + text + "'");
    }
}
