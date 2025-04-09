package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class NO_CATCH_STRICT {
    public static void main(String[] args) {
        try {
            incorrectTest();
            correctTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Некорректная обработка без try-catch
    public static void incorrectTest() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("missing.txt"); // Может выбросить исключение
        System.out.println("Файл открыт (но мы этого не увидим при ошибке)");
    }

    // Корректная обработка с try-catch
    public static void correctTest() {
        try {
            FileInputStream fis = new FileInputStream("missing.txt");
            System.out.println("Файл открыт");
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден");
            // Логирование или обработка ошибки
        } finally {
            System.out.println("Блок finally выполнен");
        }
    }
}