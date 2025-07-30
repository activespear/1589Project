package miem.projects.vulnerabilities.MAJOR_1st;

import java.util.Scanner;

public class TAINTED_INT_OVERFLOW {

    // Небезопасная функция
    static void unsafeBufferAllocation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = sc.nextInt();
        // Возможен int overflow при size * 4
        byte[] buffer = new byte[size * 4];
        System.out.println("Unsafe buffer of size: " + buffer.length);
    }

    // Безопасная функция
    static void safeBufferAllocation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = sc.nextInt();

        if (size > 0 && size <= (Integer.MAX_VALUE / 4)) {
            byte[] buffer = new byte[size * 4];
            System.out.println("Safe buffer of size: " + buffer.length);
        } else {
            System.out.println("Invalid size input: " + size);
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeBufferAllocation:");
        try {
            unsafeBufferAllocation();
        } catch (Exception e) {
            System.out.println("Exception in unsafeBufferAllocation: " + e);
        }

        System.out.println("\nRunning safeBufferAllocation:");
        try {
            safeBufferAllocation();
        } catch (Exception e) {
            System.out.println("Exception in safeBufferAllocation: " + e);
        }
    }
}

