package miem.projects.vulnerabilities.MAJOR.FB;

public class UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        var example = new UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD();

        System.out.println("unusedPublicField = " + example.unusedPublicField);
        System.out.println("unusedProtectedField = " + example.unusedProtectedField);
    }

    public static void correctTest() {
        var example = new UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD();
        example.usedPublicField = 100;
        example.usedProtectedField = "Test";

        System.out.println("usedPublicField = " + example.usedPublicField);
        System.out.println("usedProtectedField = " + example.usedProtectedField);
    }

    public int unusedPublicField;
    protected String unusedProtectedField;

    public int usedPublicField;
    protected String usedProtectedField;
}