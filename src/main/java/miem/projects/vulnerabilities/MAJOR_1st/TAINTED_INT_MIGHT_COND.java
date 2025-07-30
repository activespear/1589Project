package miem.projects.vulnerabilities.MAJOR_1st;

import java.util.Scanner;

public class TAINTED_INT_MIGHT_COND {

    // Небезопасная версия: без проверки ввода, с условием mode < 20 меняется size
    public static void unsafeAllocIf(int size, int mode) {
        if (mode > 10) {
            return;
        }
        allocSlice(size);
    }

    // Безопасная версия: с проверкой size > 0 и обработкой NumberFormatException
    public static void safeAllocIf(int size, int mode) {
        if (mode > 10 || size <= 0) {
            return;
        }
        allocSlice(size);
    }

    // Общая функция выделения массива
    public static String[] allocSlice(int size) {
        String[] s = new String[size];
        System.out.println("Allocated array of size: " + size);
        return s;
    }

    public static void unsafeMain() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Unsafe - Enter size: ");
        String sizeArg = scanner.nextLine();
        System.out.print("Unsafe - Enter mode: ");
        String modeArg = scanner.nextLine();

        int size = Integer.parseInt(sizeArg);
        int mode = Integer.parseInt(modeArg);

        if (mode < 20) {
            size = 10;
        }

        unsafeAllocIf(size, mode);
    }

    public static void safeMain() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Safe - Enter size: ");
        String sizeArg = scanner.nextLine();
        System.out.print("Safe - Enter mode: ");
        String modeArg = scanner.nextLine();

        int size = 0;
        int mode = 0;

        try {
            size = Integer.parseInt(sizeArg);
            mode = Integer.parseInt(modeArg);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
            return;
        }

        if (mode < 20) {
            size = 10;
        }

        safeAllocIf(size, mode);
    }

    public static void main(String[] args) {
        unsafeMain();
        safeMain();
    }
}

