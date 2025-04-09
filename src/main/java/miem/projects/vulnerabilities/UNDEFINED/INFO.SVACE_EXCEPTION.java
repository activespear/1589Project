package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class INFO_SVACE_EXCEPTION {
    public static void main(String[] args) {
        try {
            incorrectTest();
            correctTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Некорректная обработка исключения
    public static void incorrectTest() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("nonexistent.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Произошла ошибка");  // ОШИБКА: потеря информации
        }
    }

    // Корректная обработка исключения
    public static void correctTest() {
        try {
            FileInputStream fis = new FileInputStream("nonexistent.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка при открытии файла: " + e.getMessage());
            // Дополнительная обработка или логирование
        }
    }
}