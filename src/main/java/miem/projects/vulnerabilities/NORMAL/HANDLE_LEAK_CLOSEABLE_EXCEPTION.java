package miem.projects.vulnerabilities.NORMAL;

import java.io.*;

public class HANDLE_LEAK_CLOSEABLE_EXCEPTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("test.txt"));
            String line = reader.readLine(); // Возможное исключение
            System.out.println(line);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении: " + e.getMessage());
        } finally {
            // закрытие без учёта исключения в close()
            try {
                if (reader != null) {
                    reader.close(); // Может выбросить исключение, которое не обрабатывается
                }
            } catch (IOException e) {
                // Исключение подавлено или не логируется — ресурс может утечь незаметно
                System.err.println("Ошибка при закрытии ресурса: " + e.getMessage());
            }
        }
    }

    public static void correctTest() {
        // try-with-resources обрабатывает исключения при закрытии
        try (BufferedReader reader = new BufferedReader(new FileReader("test.txt"))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}