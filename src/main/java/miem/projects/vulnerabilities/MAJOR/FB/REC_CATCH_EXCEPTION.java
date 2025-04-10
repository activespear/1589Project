package miem.projects.vulnerabilities.MAJOR.FB;

public class REC_CATCH_EXCEPTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            System.out.println("код не выбрасывает Exception");
        } catch (Exception e) {
            System.out.println("Перехват Exception, но внутри try нет его источника");
        }
    }

    public static void correctTest() {
        try {
            throw new IllegalArgumentException("Пример RuntimeException");
        } catch (IllegalArgumentException e) {
            System.out.println("Перехвачена IllegalArgumentException: " + e.getMessage());
        }
    }
}