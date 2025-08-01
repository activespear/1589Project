package miem.projects.vulnerabilities.MAJOR_1st;

public class WRONG_SEMICOLON {

    public static void unsafeIf(int x) {
        // Небезопасная конструкция: лишняя точка с запятой после if
        if (x > 0);
        System.out.println("unsafeIf: x is positive");
    }

    public static void safeIf(int x) {
        // Безопасная конструкция: без лишней точки с запятой
        if (x > 0)
            System.out.println("safeIf: x is positive");
    }

    public static void main(String[] args) {
        System.out.println("Testing unsafeIf with x = 1:");
        unsafeIf(1);

        System.out.println("\nTesting safeIf with x = 1:");
        safeIf(1);

        System.out.println("\nTesting unsafeIf with x = -1:");
        unsafeIf(-1);

        System.out.println("\nTesting safeIf with x = -1:");
        safeIf(-1);
    }
}

