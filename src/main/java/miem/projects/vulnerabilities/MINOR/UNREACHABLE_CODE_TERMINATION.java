package miem.projects.vulnerabilities.MINOR;

public class UNREACHABLE_CODE_TERMINATION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное: код после System.exit недостижим
    public static void incorrectTest() {
        System.out.println("Before shutdown (incorrect)");
        System.exit(0);
        System.out.println("This line is unreachable"); // никогда не выполнится
    }

    // Корректная конструкция: без недостижимого кода
    public static void correctTest() {
        System.out.println("Before shutdown (correct)");
        System.exit(0);
    }
}
