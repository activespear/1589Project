package miem.projects.vulnerabilities.MAJOR_1st;

public class FALL_THROUGH {

    // Небезопасная конструкция: отсутствие break приводит к "проваливанию"
    public static void unsafeSwitch() {
        int level = 2;
        System.out.println("Небезопасный switch:");
        switch (level) {
            case 1:
                System.out.println("Low");
            case 2:
                System.out.println("Medium");
            case 3:
                System.out.println("High");
        }
    }

    // Безопасная конструкция: использование break предотвращает "проваливание"
    public static void safeSwitch() {
        int level = 2;
        System.out.println("Безопасный switch:");
        switch (level) {
            case 1:
                System.out.println("Low");
                break;
            case 2:
                System.out.println("Medium");
                break;
            case 3:
                System.out.println("High");
                break;
        }
    }

    public static void main(String[] args) {
        unsafeSwitch();
        System.out.println();
        safeSwitch();
    }
}

