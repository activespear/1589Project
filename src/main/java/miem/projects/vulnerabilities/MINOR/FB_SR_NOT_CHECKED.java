package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class FB_SR_NOT_CHECKED {

    // Потенциально небезопасное использование метода skip()
    static class ExampleUnsafe {
        public void skipData(String fileName) throws IOException {
            InputStream inputStream = new FileInputStream(fileName);
            long bytesSkipped = inputStream.skip(1024);
            //  Возвращаемое значение игнорируется, возможны ошибки при обработке данных
        }
    }

    // Корректная конструкция
    static class ExampleSafe {
        public void skipData(String fileName) throws IOException {
            InputStream inputStream = new FileInputStream(fileName);
            long bytesSkipped = inputStream.skip(1024);

            //  Проверяем, сколько байтов реально было пропущено
            if (bytesSkipped < 1024) {
                System.out.println("Пропущено только " + bytesSkipped + " байтов.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ExampleSafe example = new ExampleSafe();
        example.skipData("test.txt");
        System.out.println("Data skipped safely with checked bytes");
    }
}
