package miem.projects.vulnerabilities.MAJOR_1st;

public class SIMILAR_BRANCHES_SWITCH {

    static void unsafeSwitch(int status) {
        switch (status) {
            case 1:
                System.out.println("Error");
                break;
            case 2:
                System.out.println("Error");
                break;
            default:
                System.out.println("Unknown status");
        }
    }

    static void safeSwitch(int status) {
        switch (status) {
            case 1:
                System.out.println("Input error");
                break;
            case 2:
                System.out.println("System error");
                break;
            default:
                System.out.println("Unknown status");
        }
    }

    public static void main(String[] args) {
        System.out.println("Unsafe switch with status=1:");
        unsafeSwitch(1);

        System.out.println("Unsafe switch with status=2:");
        unsafeSwitch(2);

        System.out.println("Safe switch with status=1:");
        safeSwitch(1);

        System.out.println("Safe switch with status=2:");
        safeSwitch(2);

        System.out.println("Safe switch with status=3:");
        safeSwitch(3);
    }
}

