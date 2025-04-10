package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_LOCAL_SELF_COMPUTATION {
    public static void main(String[] args) {
        incorrectTest(5);
        correctTest(5);
    }

    public static void incorrectTest(int value) {
        boolean test = (value & value) != 0;
    }

    public static void correctTest(int value) {
        int value1 = 1;
        boolean test = (value & value1) != 0;
    }
}