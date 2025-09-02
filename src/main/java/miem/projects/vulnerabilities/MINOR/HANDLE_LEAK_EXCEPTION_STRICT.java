package miem.projects.vulnerabilities.MINOR;

import java.io.FileOutputStream;
import java.io.IOException;

public class HANDLE_LEAK_EXCEPTION_STRICT {
    public static void main(String[] args) throws IOException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws IOException {
        FileOutputStream out = new FileOutputStream("output.txt");
        out.close();
        int predictedFd = 1; // Предполагаемый дескриптор
        new FileOutputStream(predictedFd); // Попытка повторного использования
    }

    public static void correctTest() throws IOException {
        FileOutputStream out = new FileOutputStream("output.txt");
        out.write("data".getBytes());
        out.close();
        System.out.println("Файл записан корректно");
    }
}
