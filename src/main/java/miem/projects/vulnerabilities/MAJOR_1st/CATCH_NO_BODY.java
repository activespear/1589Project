package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CATCH_NO_BODY {

    // Небезопасная конструкция: пустой блок catch
    public static void unsafeRead() {
        try {
            Files.readAllBytes(Paths.get("data.txt"));
        } catch (IOException e) {
            // Ничего не делается, ошибка игнорируется
        }
    }

    // Безопасная конструкция: логгирование или сообщение об ошибке
    public static void safeRead() {
        try {
            Files.readAllBytes(Paths.get("data.txt"));
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Вызов небезопасной версии:");
        unsafeRead();

        System.out.println("Вызов безопасной версии:");
        safeRead();
    }
}

