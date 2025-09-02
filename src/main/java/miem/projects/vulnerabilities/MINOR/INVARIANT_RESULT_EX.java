package miem.projects.vulnerabilities.MINOR;

public class INVARIANT_RESULT_EX {
    public static void main(String[] args) {
        int x = 7;
        incorrectTest(x);
        correctTest(x);
    }

    // Потенциально небезопасное: условие всегда ложно
    public static void incorrectTest(int x) {
        if (x > 10 && x < 5) {
            System.out.println("Impossible!");
        }
    }

    // Корректная конструкция: условие имеет смысл
    public static void correctTest(int x) {
        if (x > 5 && x < 10) {
            System.out.println("Valid range");
        }
    }
}
