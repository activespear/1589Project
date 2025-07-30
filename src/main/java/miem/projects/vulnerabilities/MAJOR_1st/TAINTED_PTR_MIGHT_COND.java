package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TAINTED_PTR_MIGHT_COND {

    // Небезопасный метод: может выйти за пределы массива, если длина строки > 10
    public static void unsafeCopy() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter input (unsafe): ");
        String userInput = reader.readLine();

        char[] buffer = new char[10];

        if (userInput.length() > 5) {
            for (int i = 0; i < userInput.length(); i++) {
                // Может выбросить ArrayIndexOutOfBoundsException
                buffer[i] = userInput.charAt(i);
            }
            System.out.println("Unsafe copy done.");
        } else {
            System.out.println("Input length <= 5, nothing copied in unsafeCopy.");
        }
    }

    // Безопасный метод: копирует не более длины буфера
    public static void safeCopy() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter input (safe): ");
        String userInput = reader.readLine();

        char[] buffer = new char[10];

        int lengthToCopy = Math.min(userInput.length(), buffer.length);

        for (int i = 0; i < lengthToCopy; i++) {
            buffer[i] = userInput.charAt(i);
        }
        System.out.println("Safe copy done.");
    }

    public static void main(String[] args) {
        try {
            System.out.println("Running unsafeCopy:");
            unsafeCopy();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught in unsafeCopy: " + e);
        } catch (Exception e) {
            System.out.println("Exception in unsafeCopy: " + e);
        }

        try {
            System.out.println("\nRunning safeCopy:");
            safeCopy();
        } catch (Exception e) {
            System.out.println("Exception in safeCopy: " + e);
        }
    }
}
