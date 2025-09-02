package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class FB_OS_OPEN_STREAM {

    // Потенциально небезопасное использование InputStream
    static class ExampleUnsafe {
        public void readFile(String fileName) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(fileName); // Поток создан, но не закрыт
                // Чтение из потока
            } catch (IOException e) {
                e.printStackTrace();
            }
            // ❌ Поток не закрыт, возможен ресурсный leak
        }
    }

    // Корректная конструкция
    static class ExampleSafe {
        public void readFile(String fileName) {
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(fileName); // Поток создан
                // Чтение из потока
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close(); // ✅ Поток закрывается в блоке finally
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExampleSafe example = new ExampleSafe();
        example.readFile("test.txt");
        System.out.println("File read safely");
    }
}
