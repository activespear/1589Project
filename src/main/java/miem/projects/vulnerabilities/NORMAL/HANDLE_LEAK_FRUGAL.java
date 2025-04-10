package miem.projects.vulnerabilities.NORMAL;

import java.io.FileInputStream;
import java.io.IOException;

public class HANDLE_LEAK_FRUGAL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("test.txt");
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Файл не закрывается здесь или в блоке finally, утечка дескриптора
    }

    public static void correctTest() {
        try (FileInputStream fis = new FileInputStream("test.txt")) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}