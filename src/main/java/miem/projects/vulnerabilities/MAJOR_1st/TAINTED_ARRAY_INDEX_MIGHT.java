package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.IOException;

public class TAINTED_ARRAY_INDEX_MIGHT {

    // Небезопасная конструкция
    static void unsafeArrayIndex(boolean flag) throws IOException {
        int[] buf = new int[256];
        int index = System.in.read();

        if (flag && index < 256) {
            buf[index] = 7;  // Возможен выход за границы, если index < 0
            System.out.println("Unsafe assignment done at index " + index);
        } else {
            System.out.println("Unsafe assignment skipped.");
        }
    }

    // Безопасная конструкция
    static void safeArrayIndex(boolean flag) throws IOException {
        int[] buf = new int[256];
        int index = System.in.read();

        if (flag && index >= 0 && index < 256) {
            buf[index] = 7;  // Проверка предотвращает выход за границы массива
            System.out.println("Safe assignment done at index " + index);
        } else {
            System.out.println("Safe assignment skipped due to invalid index.");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Running unsafeArrayIndex with flag=true:");
            unsafeArrayIndex(true);
        } catch (IOException e) {
            System.out.println("Exception in unsafeArrayIndex: " + e);
        }

        try {
            System.out.println("\nRunning safeArrayIndex with flag=true:");
            safeArrayIndex(true);
        } catch (IOException e) {
            System.out.println("Exception in safeArrayIndex: " + e);
        }
    }
}

