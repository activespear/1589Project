package miem.projects.vulnerabilities.MAJOR.FB;

public class ICAST_INT_CAST_TO_FLOAT_PASSED_TO_ROUND {

    // Небезопасная конструкция: int → float → Math.round(), избыточно
    public static void runUnsafe() {
        int number = 42;
        int result = Math.round((float) number);  // Ненужное приведение и округление
        System.out.println("Unsafe result: " + result);
    }

    // Безопасная конструкция: просто используем int напрямую
    public static void runSafe() {
        int number = 42;
        int result = number;  // Никакой необходимости в Math.round
        System.out.println("Safe result: " + result);
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe:");
        runUnsafe();
        System.out.println();

        System.out.println("Running safe:");
        runSafe();
    }
}

