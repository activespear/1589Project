package miem.projects.vulnerabilities.MAJOR.FB;

public class UCF_USELESS_CONTROL_FLOW_NEXT_LINE {
    public static void main(String[] args) {
        incorrectTest(5);
        correctTest(5);
    }

    public static void incorrectTest(int x) {
        if (x > 0);
        System.out.println("Код выполняется независимо от if.");
    }

    public static void correctTest(int x) {
        if (x > 0) {
            System.out.println("x > 0");
        }
        System.out.println("...");
    }
}

