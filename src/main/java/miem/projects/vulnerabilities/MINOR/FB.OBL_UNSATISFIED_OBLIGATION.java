package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class OBL_UNSATISFIED_OBLIGATION {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Ресурс никогда не закрывается
        FileInputStream fis = new FileInputStream("data.txt");

        // 2. Закрытие только в happy path
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("config.ini"));
            String line = br.readLine();
            if (line.startsWith("DEBUG")) {
                return; // Утечка ресурса!
            }
            br.close(); // Закрытие только здесь
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Небезопасный try-finally
        OutputStream os = null;
        try {
            os = new FileOutputStream("out.bin");
            os.write(42);
        } finally {
            if (os != null) { // Правильная проверка, но...
                os.close(); // Может выбросить IOException
            }
        }
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. try-with-resources (Java 7+)
        try (FileInputStream fis = new FileInputStream("data.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Явное управление с защитой от исключений
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("config.ini"));
            String line = br.readLine();
            if (line.startsWith("DEBUG")) {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 3. Использование IOUtils из Apache Commons
        OutputStream os = null;
        try {
            os = new FileOutputStream("out.bin");
            os.write(42);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(os); // Безопасное закрытие
        }
    }
}