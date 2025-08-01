package miem.projects.vulnerabilities.MAJOR.FB;

public class ICAST_INT_CAST_TO_DOUBLE_PASSED_TO_CEIL {

    // Небезопасная конструкция: приведение int к double и передача в Math.ceil()
    public static void runUnsafe() {
        int number = 42;
        double result = Math.ceil((double) number);  // Избыточно: ceil(42.0) = 42.0
        System.out.println("Unsafe result: " + result);
    }

    // Безопасная конструкция: прямое приведение без ceil
    public static void runSafe() {
        int number = 42;
        double result = number;  // Просто преобразование к double
        System.out.println("Safe result: " + result);
    }

    public static void main(String[] args) {
        runUnsafe();
        runSafe();
    }
}

