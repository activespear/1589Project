package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_STRING_VOID_CTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String emptyString = new String();
        System.out.println(emptyString);
    }

    public static void correctTest() {
        String emptyString = "";
        System.out.println(emptyString);
    }
}