package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TAINTED_ARRAY_INDEX_LOOP {

    // Небезопасная функция
    static void unsafeLoop() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[100];
        System.out.print("Enter index for unsafe loop: ");
        int index = Integer.parseInt(reader.readLine());

        for (int i = 0; i < index; i++) {
            array[i] = i;  // Может выйти за пределы массива
        }
        System.out.println("Unsafe loop completed.");
    }

    // Безопасная функция
    static void safeLoop() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = new int[100];
        System.out.print("Enter index for safe loop: ");
        int index = Integer.parseInt(reader.readLine());

        if (index >= 0 && index <= array.length) {
            for (int i = 0; i < index; i++) {
                array[i] = i;  // Защищённый доступ к массиву
            }
            System.out.println("Safe loop completed.");
        } else {
            System.out.println("Index out of bounds: " + index);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Running unsafeLoop:");
            unsafeLoop();
        } catch (Exception e) {
            System.out.println("Exception in unsafeLoop: " + e);
        }

        try {
            System.out.println("\nRunning safeLoop:");
            safeLoop();
        } catch (Exception e) {
            System.out.println("Exception in safeLoop: " + e);
        }
    }
}

