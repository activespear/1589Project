package miem.projects.vulnerabilities.NORMAL;

public class DEREF_AFTER_NULL_UNCLEAR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String value = getNullable();
        // возможно value == null, а метод будет делать value.length()
        unknownMethod(value);
    }

    public static void correctTest() {
        String value = getNullable();
        if (value != null) {
            unknownMethod(value); // безопасно
        } else {
            System.out.println("Null value, method not called");
        }
    }

    public static void unknownMethod(String input) {
        // Анализатор может не знать, что происходит внутри этой функции.
        System.out.println(input.length());
    }

    public static String getNullable() {
        return Math.random() < 0.5 ? "Hello" : null;
    }
}