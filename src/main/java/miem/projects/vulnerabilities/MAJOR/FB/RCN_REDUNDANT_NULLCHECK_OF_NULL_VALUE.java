package miem.projects.vulnerabilities.MAJOR.FB;

public class RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE {
    public static void main(String[] args) {
        incorrectTest(null);
        correctTest(null);
    }

    public static void incorrectTest(Object text) {
        if (text == null) {
            System.out.println("text равен null");
        }

        if (text == null) {
            System.out.println("Эта проверка бессмысленна");
        }
    }

    public static void correctTest(Object text) {
        if (text == null) {
            System.out.println("text равен null");
        }
    }
}