package miem.projects.vulnerabilities.NORMAL;

import java.io.*;

public class HANDLE_LEAK_CLOSEABLE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("test.txt"));
            System.out.println(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void correctTest() {
        try (BufferedReader reader = new BufferedReader(new FileReader("test.txt"))) {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}