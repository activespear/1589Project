package miem.projects.vulnerabilities.NORMAL;

import java.io.FileInputStream;
import java.io.IOException;

public class DOUBLE_CLOSE {
    public static void main(String[] args) throws IOException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("test.txt");
            // Читаем данные...
        } finally {
            if (fis != null) {
                fis.close();
                fis.close();
            }
        }
    }

    public static void correctTest() {
        try (FileInputStream fis = new FileInputStream("test.txt")) {
            // Читаем данные...
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Ресурс автоматически закрывается благодаря try-with-resources
    }
}