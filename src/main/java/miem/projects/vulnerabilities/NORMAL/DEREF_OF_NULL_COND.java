package miem.projects.vulnerabilities.NORMAL;

public class DEREF_OF_NULL_COND {
    public static void main(String[] args) {
        incorrectTest(Math.random() > 0.5 ? null : "text");
        correctTest(Math.random() > 0.5 ? null : "text");
    }

    public static void incorrectTest(String maybeNull) {
        // возможен NPE
        if (maybeNull != null || maybeNull.length() > 0) {
            System.out.println(maybeNull.length());
        }
    }

    public static void correctTest(String maybeNull) {
        if (maybeNull != null && maybeNull.length() > 0) { // корректная проверка
            System.out.println(maybeNull.length());
        }
    }
}