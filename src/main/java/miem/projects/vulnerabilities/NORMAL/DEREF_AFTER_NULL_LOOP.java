package miem.projects.vulnerabilities.NORMAL;

public class DEREF_AFTER_NULL_LOOP {
    public static void main(String[] args) {
        String[] values = {"Hello", null, "World"};
        incorrectTest(values);
        correctTest(values);
    }

    public static void incorrectTest(String[] values) {
        for (String value : values) {
            System.out.println(value.length());
        }
    }

    public static void correctTest(String[] values) {
        for (String value : values) {
            if (value != null) {
                System.out.println(value.length());
            } else {
                System.out.println("Null value skipped");
            }
        }
    }
}