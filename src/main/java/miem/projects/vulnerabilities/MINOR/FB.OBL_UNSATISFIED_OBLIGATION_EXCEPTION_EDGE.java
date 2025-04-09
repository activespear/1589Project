package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;
import java.util.concurrent.*;

public class OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Исключение до try-блока
        FileInputStream fis = new FileInputStream("data.txt"); // Может выбросить FileNotFoundException
        BufferedInputStream bis = new BufferedInputStream(fis); // Может выбросить NullPointerException
        try {  // Ресурсы могут быть не закрыты при исключениях выше
            System.out.println(bis.read());
        } finally {
            bis.close(); // Не закрывает fis при исключении!
        }

        // 2. CompletableFuture с потерянными ресурсами
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<File> future = executor.submit(() -> {
            File tmp = File.createTempFile("pref", ".tmp");
            try (FileOutputStream fos = new FileOutputStream(tmp)) {
                fos.write(42);  // Если исключение здесь - tmp не удаляется
            }
            return tmp;
        });
        executor.shutdown();
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Вложенные try-with-resources
        try (FileInputStream fis = new FileInputStream("data.txt");
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            System.out.println(bis.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Управление временными файлами через Cleaner
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<File> future = executor.submit(() -> {
            File tmp = File.createTempFile("pref", ".tmp");
            tmp.deleteOnExit();  // Гарантированное удаление
            try (FileOutputStream fos = new FileOutputStream(tmp)) {
                fos.write(42);
            }
            return tmp;
        });
        
        // 3. Дополнительная защита для Future
        try {
            File result = future.get(1, TimeUnit.SECONDS);
            // Обработка результата
        } catch (Exception e) {
            future.cancel(true);  // Прерывание задачи
        } finally {
            executor.shutdown();
        }
    }
}