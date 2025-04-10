package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_STRING_TOSTRING {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String message = "Hello, world!";
        String duplicate = message.toString();

        System.out.println(duplicate);
    }

    public static void correctTest() {
        String message = "Hello, world!";
        System.out.println(message);
    }
}
