package miem.projects.vulnerabilities.MAJOR_1st;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TAINTED_PTR_COND {

    // Небезопасный метод: копирует байты без проверки длины и содержимого
    public static void unsafeCopy() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input (unsafe): ");
        String userInput = scanner.nextLine();

        byte[] buffer = new byte[100];
        byte[] inputBytes = userInput.getBytes();
        // Здесь может возникнуть ArrayIndexOutOfBoundsException, если inputBytes.length > 100
        System.arraycopy(inputBytes, 0, buffer, 0, inputBytes.length);

        System.out.println("Unsafe copy completed, bytes copied: " + inputBytes.length);
    }

    // Безопасный метод: проверяет длину и разрешённые символы перед копированием
    public static void safeCopy() {
        final int MAX_LEN = 100;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input (safe): ");
        String userInput = scanner.nextLine();

        if (userInput != null && userInput.length() <= MAX_LEN && userInput.matches("[a-zA-Z0-9_]*")) {
            byte[] inputBytes = userInput.getBytes(StandardCharsets.UTF_8);
            byte[] buffer = new byte[MAX_LEN];
            System.arraycopy(inputBytes, 0, buffer, 0, inputBytes.length);
            System.out.println("Safe copy completed, bytes copied: " + inputBytes.length);
        } else {
            System.out.println("Input is invalid or too long for safe copy.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeCopy:");
        try {
            unsafeCopy();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught in unsafeCopy: " + e);
        }

        System.out.println("\nRunning safeCopy:");
        safeCopy();
    }
}

