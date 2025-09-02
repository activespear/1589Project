package miem.projects.vulnerabilities.MINOR.FB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FB_DE_MIGHT_DROP {

    private static final Logger logger = Logger.getLogger(FB_DE_MIGHT_DROP.class.getName());

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            FileInputStream fis = new FileInputStream("file.txt");
        } catch (IOException e) {
            // Исключение проглочено, ошибка не обработана
        }

        System.out.println("File operation attempted (INSECURE)");
    }

    public static void correctTest() {
        try {
            FileInputStream fis = new FileInputStream("file.txt");
        } catch (IOException e) {
            // Логирование ошибки
            logger.log(Level.SEVERE, "Ошибка при открытии файла", e);
        }

        System.out.println("File operation attempted with logging (SECURE)");
    }
}
