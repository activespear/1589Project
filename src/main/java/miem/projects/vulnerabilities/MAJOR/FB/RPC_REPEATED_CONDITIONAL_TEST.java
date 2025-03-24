package miem.projects.vulnerabilities.MAJOR.FB;

public class RPC_REPEATED_CONDITIONAL_TEST {
    public static void main(String[] args) {
        incorrectTest(0, 5);
        correctTest(0, 5);
    }

    public static void incorrectTest(int x, int y) {
        if (x == 0 || x == 0) {
            // ...
        }
    }

    public static void correctTest(int x, int y) {
        if (x == 0 || y == 0) {
            // ...
        }
    }
}