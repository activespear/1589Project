package miem.projects.vulnerabilities.MAJOR.FB;

public class SF_DEAD_STORE_DUE_TO_SWITCH_FALLTHROUGH {
    public static void main(String[] args) {
        incorrectTest(1);
        correctTest(1);
    }

    public static void incorrectTest(int input) {
        switch (input) {
            case 1:
                // ...
            case 2:
                // Падение сквозь case
        }
    }

    public static void correctTest(int input) {
        switch (input) {
            case 1:
                // ...
                break;
            case 2:
                // ...
                break;
        }
    }
}