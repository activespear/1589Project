package miem.projects.vulnerabilities.NORMAL;

import java.io.FileInputStream;
import java.io.IOException;

public class HANDLE_LEAK_EXCEPTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("test.txt");
            // возможное исключение
            int data = fis.read();
            System.out.println(data);
        } catch (IOException e) {
            // здесь исключение обработано, но ресурс не закрыт!
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            // Если исключение произошло ДО fis.close() — файл останется открытым
        }
    }

    public static void correctTest() {
        try (FileInputStream fis = new FileInputStream("test.txt")) {
            int data = fis.read();
            System.out.println(data);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
