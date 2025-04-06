package miem.projects.vulnerabilities.NORMAL;

public class DEREF_AFTER_NULL_RET {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String value = getNullable();
        if (value == null) {
            System.out.println("Value is null");
        }
        // если value == null, это вызовет NullPointerException
        System.out.println("Length: " + value.length());
    }

    public static void correctTest() {
        String value = getNullable();
        if (value == null) {
            System.out.println("Value is null");
            return;
        }
        System.out.println("Length: " + value.length());
    }

    public static String getNullable() {
        return Math.random() < 0.5 ? "Hello" : null;
    }
}