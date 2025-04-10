package miem.projects.vulnerabilities.NORMAL;

public class REDUNDANT_COMPARISON_ALWAYS_FALSE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int x = 3;

        while (x > 5) {
            System.out.println("This will never be printed");
        }
    }

    public static void correctTest() {
        int x = 3;
        while (x < 5) {
            System.out.println("x is less than 5");
            x++;
        }
    }
}