package miem.projects.vulnerabilities.MAJOR.FB;

public class QF_QUESTIONABLE_FOR_LOOP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        for (int i = 0, j = 10; i < 10; i++, j--) {
            System.out.println("i = " + i + ", j = " + j);
        }
    }

    public static void correctTest() {
        int j = 10;
        for (int i = 0; i < 10; i++, j--) {
            System.out.println("i = " + i + ", j = " + j);
        }
    }
}