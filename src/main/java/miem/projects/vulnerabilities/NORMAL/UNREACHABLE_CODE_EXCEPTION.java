package miem.projects.vulnerabilities.NORMAL;

public class UNREACHABLE_CODE_EXCEPTION {
    public static void main(String[] args) {
        try {
            incorrectTest();
        } catch (RuntimeException e) {
            System.out.println("Main caught: " + e.getMessage());
        }
        correctTest();
    }

    public static void incorrectTest() {
        throw new RuntimeException("Something went wrong!");
        //System.out.println("This will never be printed");
    }

    public static void correctTest() {
        try {
            if (Math.random() > 0.5) {
                throw new RuntimeException("Something went wrong!");
            }
        } catch (RuntimeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("This line is reachable");
    }
}