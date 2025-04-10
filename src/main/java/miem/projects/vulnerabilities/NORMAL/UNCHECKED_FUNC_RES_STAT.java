package miem.projects.vulnerabilities.NORMAL;

public class UNCHECKED_FUNC_RES_STAT {
    public static void main(String[] args) {
        incorrectTest("a");
        correctTest("a");
    }

    public static void incorrectTest(String s) {
        // результат не защищён, может упасть
        int value = Integer.parseInt(s);
        System.out.println("Parsed: " + value);
    }

    public static void correctTest(String s) {
        try {
            int value = Integer.parseInt(s);
            System.out.println("Parsed: " + value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }
}