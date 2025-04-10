package miem.projects.vulnerabilities.MAJOR.FB;

public class UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        var example = new UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD();
        example.unusedPublicField = 100; // Поле инициализируется, но не используется
        example.unusedProtectedField = "Test";
    }

    public static void correctTest() {
        var example = new UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD();
        example.usedPublicField = 100;
        example.usedProtectedField = "Test";

        System.out.println(example.usedPublicField);
        System.out.println(example.usedProtectedField);
    }

    public int unusedPublicField;
    protected String unusedProtectedField;

    public int usedPublicField;
    protected String usedProtectedField;
}