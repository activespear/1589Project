package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_LOCAL_SELF_COMPARISON {
    public static void main(String[] args) {
        incorrectTest(5);
        correctTest(5);
    }

    public static void incorrectTest(int value) {
        // всегда true
        boolean test = value == value;
    }

    public static void correctTest(int value) {
        int value1 = 0;
        boolean test = value == value1;
    }
}