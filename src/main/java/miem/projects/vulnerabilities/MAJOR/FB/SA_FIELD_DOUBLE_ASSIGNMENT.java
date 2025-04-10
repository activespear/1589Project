package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_FIELD_DOUBLE_ASSIGNMENT {
    private static int value;

    public static void main(String[] args) {
        incorrectTest(42);
        correctTest(42);
    }

    public static void incorrectTest(int newValue) {
        value = 10;
        value = newValue;
        System.out.println("Некорректное значение: " + value);
    }

    public static void correctTest(int newValue) {
        if (value != newValue) {
            value = newValue;
        }
        System.out.println("Корректное значение: " + value);
    }
}