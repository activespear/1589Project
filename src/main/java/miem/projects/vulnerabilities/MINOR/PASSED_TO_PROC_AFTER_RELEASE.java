package miem.projects.vulnerabilities.MINOR;

import java.io.FileInputStream;
import java.io.IOException;

public class PASSED_TO_PROC_AFTER_RELEASE {
    public static void main(String[] args) throws IOException {
        incorrectTest();
        correctTest();
    }

    // Потенциально небезопасное: чтение после закрытия потока
    public static void incorrectTest() throws IOException {
        FileInputStream fis = new FileInputStream("file.txt");
        fis.close();
        fis.read(); // Использование после закрытия
    }

    // Корректное использование: чтение до закрытия потока
    public static void correctTest() throws IOException {
        FileInputStream fis = new FileInputStream("file.txt");
        fis.read();
        fis.close();
        System.out.println("Файл прочитан корректно");
    }
}
