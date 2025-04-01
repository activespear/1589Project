package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_STRING_CTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String original = "Hello, world!";
        String duplicate = new String(original);

        System.out.println(duplicate);
    }

    public static void correctTest() {
        String original = "Hello, world!";
        String duplicate = original;

        System.out.println(duplicate);
    }
}