package miem.projects.vulnerabilities.MAJOR.FB;

public class DMI_USELESS_SUBSTRING {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String str = "Hello, world!";
        String uselessSubstring = str.substring(0);
        System.out.println(uselessSubstring);
    }

    public static void correctTest() {
        String str = "Hello, world!";
        String correctString = str;
        System.out.println(correctString);
    }
}