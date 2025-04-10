package miem.projects.vulnerabilities.MAJOR.FB;

public class SF_SWITCH_FALLTHROUGH {
    public static void main(String[] args) {
        incorrectTest(2);
        correctTest(2);
    }

    public static void incorrectTest(int value) {
        switch (value) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                // Отсутствует break, что приводит к переходу к следующему case
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("default");
        }
    }

    public static void correctTest(int value) {
        switch (value) {
            case 1:
                System.out.println("q");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("default");
        }
    }
}