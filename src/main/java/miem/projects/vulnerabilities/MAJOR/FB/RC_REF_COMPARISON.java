package miem.projects.vulnerabilities.MAJOR.FB;

public class RC_REF_COMPARISON {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Integer a = 1000;
        Integer b = 1000;
        System.out.println(a == b);
    }

    public static void correctTest() {
        Integer a = 1000;
        Integer b = 1000;
        System.out.println(a.equals(b));
    }
}