package miem.projects.vulnerabilities.MAJOR_1st;

import java.util.Scanner;

public class TAINTED_INT_COND {

    // Небезопасная версия
    public static void unsafeAllocIf(int size, int mode) {
        if (mode > 10) {
            return;
        }
        allocSlice(size);
        System.out.println("Unsafe allocation done for size: " + size);
    }

    public static String[] allocSlice(int size) {
        String[] s = new String[size];
        return s;
    }

    // Безопасная версия
    public static void safeAllocIf(int size, int mode) {
        if (mode > 10 || size <= 0) {
            System.out.println("Safe allocation skipped due to invalid size or mode.");
            return;
        }
        allocSlice(size);
        System.out.println("Safe allocation done for size: " + size);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Чтение и запуск небезопасной версии
        System.out.println("Unsafe version:");
        System.out.print("Enter size: ");
        String sizeArgUnsafe = scanner.nextLine();
        System.out.print("Enter mode: ");
        String modeArgUnsafe = scanner.nextLine();

        int sizeUnsafe = Integer.parseInt(sizeArgUnsafe);
        int modeUnsafe = Integer.parseInt(modeArgUnsafe);

        unsafeAllocIf(sizeUnsafe, modeUnsafe);

        // Чтение и запуск безопасной версии
        System.out.println("\nSafe version:");
        System.out.print("Enter size: ");
        String sizeArgSafe = scanner.nextLine();
        System.out.print("Enter mode: ");
        String modeArgSafe = scanner.nextLine();

        int sizeSafe = 0;
        int modeSafe = 0;
        try {
            sizeSafe = Integer.parseInt(sizeArgSafe);
            modeSafe = Integer.parseInt(modeArgSafe);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
            scanner.close();
            return;
        }

        safeAllocIf(sizeSafe, modeSafe);
        scanner.close();
    }
}

