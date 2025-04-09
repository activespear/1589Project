package miem.projects.vulnerabilities.MINOR.FB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FB_NP_DEREFERENCE_OF_READLINE_VALUE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: прямое использование readLine()
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line.toUpperCase());  // Опасное разыменование
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        // Корректно: проверка на null перед использованием
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null) {  // Дополнительная проверка
                    System.out.println(line.toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}