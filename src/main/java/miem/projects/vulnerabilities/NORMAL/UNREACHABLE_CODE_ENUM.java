package miem.projects.vulnerabilities.NORMAL;

public class UNREACHABLE_CODE_ENUM {
    public static void main(String[] args) {
        incorrectTest(Day.MONDAY);
        correctTest(Day.TUESDAY);
    }

    enum Day {
        MONDAY, TUESDAY
    }

    public static void incorrectTest(Day day) {
        switch (day) {
            case MONDAY:
                System.out.println("It's Monday");
                break;
            case TUESDAY:
                System.out.println("It's Tuesday");
                break;
            default:
                System.out.println("This should never happen");
        }
    }

    public static void correctTest(Day day) {
        switch (day) {
            case MONDAY:
                System.out.println("It's Monday");
                break;
            case TUESDAY:
                System.out.println("It's Tuesday");
                break;
            // все значения enum обработаны
        }
    }
}