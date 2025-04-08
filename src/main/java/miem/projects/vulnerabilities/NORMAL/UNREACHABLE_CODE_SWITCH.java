package miem.projects.vulnerabilities.NORMAL;

public class UNREACHABLE_CODE_SWITCH {
    public static void main(String[] args) {
        incorrectTest(7);
        correctTest(7);
    }

    public static void incorrectTest(int value) {
        int x = value % 2;
        switch (x) {
            case 0:
                System.out.println("x is 0");
                break;
            case 1:
                System.out.println("x is 1");
                break;
            case 2: // Этот case всегда будет выполнен
                System.out.println("x is 2");
                break;
        }
    }

    public static void correctTest(int value) {
        int x = value % 2;
        switch (x) {
            case 0:
                System.out.println("x is 0");
                break;
            case 1:
                System.out.println("x is 1");
                break;
        }
    }
}