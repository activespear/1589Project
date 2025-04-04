package miem.projects.vulnerabilities.MAJOR.FB;

public class URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        var example = new URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD();
        example.unusedPublicField = 42;  // Присваивание, но поле не используется
        example.unusedProtectedField = "Hello";
    }

    public static void correctTest() {
        var example = new URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD();
        example.usedPublicField = 42;
        example.usedProtectedField = "Hello";

        System.out.println( example.usedPublicField);
        System.out.println(example.usedProtectedField);
    }

    public int usedPublicField;
    protected String usedProtectedField;

    public int unusedPublicField;
    protected String unusedProtectedField;
}