package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DE_MIGHT_IGNORE {

    // Небезопасная конструкция — игнорируется FileNotFoundException
    public static void unsafeReadFile(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            // .. (чтение файла)
            file.close();
        } catch (FileNotFoundException e) {
            // Исключение поймано, но ничего не сделано — игнорирование
        }
    }

    // Безопасная конструкция — обработка исключений с выводом информации
    public static void safeReadFile(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            // .. (чтение файла)
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runUnsafe() {
        System.out.println("Запуск небезопасного метода:");
        unsafeReadFile("non_existing_file.txt");
    }

    public static void runSafe() {
        System.out.println("Запуск безопасного метода:");
        safeReadFile("non_existing_file.txt");
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

