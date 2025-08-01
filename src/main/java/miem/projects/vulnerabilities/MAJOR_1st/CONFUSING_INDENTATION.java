package miem.projects.vulnerabilities.MAJOR_1st;

public class CONFUSING_INDENTATION {

    // Небезопасная (запутанная из-за отступов) реализация
    public static void confusingIndentation() {
        int x = 10;
        if (x > 5)
        {
            System.out.println("Greater");
        }
        else {
            System.out.println("Less or equal");
        }
    }

    // Безопасная, читаемая реализация
    public static void clearIndentation() {
        int x = 10;
        if (x > 5) {
            System.out.println("Greater");
        } else {
            System.out.println("Less or equal");
        }
    }

    public static void main(String[] args) {
        System.out.println("== Небезопасная реализация ==");
        confusingIndentation();

        System.out.println("\n== Безопасная реализация ==");
        clearIndentation();
    }
}
