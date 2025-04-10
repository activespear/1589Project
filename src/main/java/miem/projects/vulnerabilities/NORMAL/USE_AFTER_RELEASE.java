package miem.projects.vulnerabilities.NORMAL;

import java.io.FileInputStream;
import java.io.IOException;

public class USE_AFTER_RELEASE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("test.txt");
            fileInputStream.close();
            // Попытка использовать после закрытия — ошибка
            fileInputStream.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void correctTest() {
        try (FileInputStream fileInputStream = new FileInputStream("test.txt")) {
            int data = fileInputStream.read();
            System.out.println(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}