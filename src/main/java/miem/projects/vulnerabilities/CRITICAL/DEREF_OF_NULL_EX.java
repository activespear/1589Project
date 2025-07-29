package miem.projects.vulnerabilities.CRITICAL;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DEREF_OF_NULL_EX {

    // Небезопасная функция: возможен NullPointerException
    public static void unsafeFileHandling(File f) {
        File[] files = f.listFiles(); // может вернуть null
        // Если files == null, будет выброшено исключение
        handleCollection(List.of(files));
    }

    // Безопасная функция: null-защищённая обработка
    public static void safeFileHandling(File f) {
        File[] files = f.listFiles();
        Collection<Object> safeList = files != null ? Arrays.asList(files) : Collections.emptyList();
        handleCollectionCorrect(safeList);
    }

    // Общая функция для обработки коллекции (используется и там, и там)
    public static void handleCollection(Collection<Object> collection) {
        for (Object elem : collection) {
            System.out.println("Обработка файла: " + elem);
        }
    }

    // Безопасная версия обработчика (аналог handleCollection)
    public static void handleCollectionCorrect(Collection<Object> collection) {
        for (Object elem : collection) {
            System.out.println("Обработка файла (safe): " + elem);
        }
    }

    public static void main(String[] args) {
        // Путь к существующему каталогу (можно заменить на любой доступный каталог)
        File existingDir = new File(".");

        // Путь к несуществующему каталогу (вернёт null в listFiles())
        File nonExistingDir = new File("nonexistent_directory");

        System.out.println("=== Безопасная обработка существующего каталога ===");
        safeFileHandling(existingDir);

        System.out.println("\n=== Безопасная обработка несуществующего каталога ===");
        safeFileHandling(nonExistingDir);

        System.out.println("\n=== Небезопасная обработка несуществующего каталога ===");
        try {
            unsafeFileHandling(nonExistingDir);
        } catch (NullPointerException e) {
            System.out.println("Произошло исключение: " + e);
        }
    }
}

