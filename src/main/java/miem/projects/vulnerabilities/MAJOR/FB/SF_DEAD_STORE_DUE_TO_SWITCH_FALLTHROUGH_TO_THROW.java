package miem.projects.vulnerabilities.MAJOR.FB;

public class SF_DEAD_STORE_DUE_TO_SWITCH_FALLTHROUGH_TO_THROW {
    public static void main(String[] args) {
        incorrectTest(1);
        correctTest(1);
    }

    public static void incorrectTest(int input) {
        switch (input) {
            case 1:
                // ...
            case 2:
                throw new IllegalArgumentException();
        }
    }

    public static void correctTest(int input) {
        switch (input) {
            case 1:
                // ...
                break;
            case 2:
                throw new IllegalArgumentException();
        }
    }
}