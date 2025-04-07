package miem.projects.vulnerabilities.NORMAL;

import java.io.File;

public class TAINTED_INT_OVERFLOW_TRUNC {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        File file = new File("large_file.bin");
        // может вернуть значение больше Integer.MAX_VALUE
        long fileSize = file.length();

        int bufferSize = (int) fileSize; // УСЕЧЕНИЕ
        byte[] buffer = new byte[bufferSize]; // ArrayIndexOutOfBounds или OutOfMemory

        System.out.println(buffer.length);
    }

    public static void correctTest() {
        File file = new File("large_file.bin");
        long fileSize = file.length();

        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("Файл слишком большой для обработки в массиве int.");
        } else {
            int bufferSize = (int) fileSize;
            byte[] buffer = new byte[bufferSize];
            System.out.println(buffer.length);
        }
    }
}