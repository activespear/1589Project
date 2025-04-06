package miem.projects.vulnerabilities.NORMAL;

public class DEREF_OF_NULL_RET_ASSERT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String value = getMaybeNull();
        // попытка разыменования null в assert
        assert value != null : value.length();
    }

    public static void correctTest() {
        String value = getMaybeNull();
        assert value != null : "value is null";
    }

    public static String getMaybeNull() {
        return Math.random() > 0.5 ? "Hello": null;
    }
}