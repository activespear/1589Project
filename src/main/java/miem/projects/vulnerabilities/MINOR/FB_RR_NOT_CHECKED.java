package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class FB_RR_NOT_CHECKED {

    // Потенциально небезопасное использование InputStream
    static class ExampleUnsafe {
        public void readFile(String fileName) throws IOException {
            InputStream inputStream = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // ❌ Количество прочитанных байтов не проверяется, возможны ошибки обработки данных
                // Другие операции с данными
            }
        }
    }

    // Корректная конструкция
    static class ExampleSafe {
        public void readFile(String fileName) throws IOException {
            InputStream inputStream = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // ✅ Проверяем количество прочитанных байтов и обрабатываем их
                if (bytesRead < buffer.length) {
                    System.out.println("Чтение завершено. Прочитано " + bytesRead + " байтов.");
                }
                // Другие операции с данными
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ExampleSafe example = new ExampleSafe();
        example.readFile("test.txt");
        System.out.println("File read safely with checked bytes");
    }
}
