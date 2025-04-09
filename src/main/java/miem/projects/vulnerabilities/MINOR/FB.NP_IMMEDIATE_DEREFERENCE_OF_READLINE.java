package miem.projects.vulnerabilities.MINOR.FB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FB_NP_IMMEDIATE_DEREFERENCE_OF_READLINE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: немедленное разыменование readLine()
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            System.out.println(br.readLine().toUpperCase());  // Прямое разыменование
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        // Корректно: проверка на null перед использованием
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line = br.readLine();
            if (line != null) {
                System.out.println(line.toUpperCase());
            } else {
                System.out.println("File is empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}