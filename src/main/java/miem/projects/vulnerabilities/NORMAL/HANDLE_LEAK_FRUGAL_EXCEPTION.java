package miem.projects.vulnerabilities.NORMAL;

import java.io.FileInputStream;
import java.io.IOException;

public class HANDLE_LEAK_FRUGAL_EXCEPTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("test.txt");
            int data = fis.read(); // Возможное исключение
            System.out.println(data);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close(); // Это хорошо, но не покрывает все сценарии
                }
            } catch (IOException closeEx) {
                System.err.println("Ошибка при закрытии файла: " + closeEx.getMessage());
            }
        }
    }

    public static void correctTest() {
        // Более надёжный способ — try-with-resources
        try (FileInputStream fis = new FileInputStream("test.txt")) {
            int data = fis.read();
            System.out.println(data);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении или закрытии файла: " + e.getMessage());
        }
    }
}