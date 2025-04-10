package miem.projects.vulnerabilities.MAJOR.FB;

public class INT_VACUOUS_COMPARISON {
    public static void main(String[] args) {
        incorrectTest(1);
        correctTest(1, 2);
    }

    public static void incorrectTest(int x) {
        // всегда истинно
        if (x <= Integer.MAX_VALUE) {
            System.out.println("x <= Integer.MAX_VALUE is always true.");
        }

        // всегда ложно
        if (x >= Integer.MIN_VALUE) {
            System.out.println("x >= Integer.MIN_VALUE is always true.");
        }
        x = 42;
        if (x != 42) {  // всегда false
            System.out.println("This will never happen.");
        }
    }

    public static void correctTest(int x, int y) {
        if (x < Integer.MAX_VALUE) {
            System.out.println("x is less than Integer.MAX_VALUE.");
        }

        if (x != y) {
            System.out.println("x is not equal to y.");
        }

        if (x > 10 && x < 100) {
            System.out.println("x is between 10 and 100.");
        }
    }
}
