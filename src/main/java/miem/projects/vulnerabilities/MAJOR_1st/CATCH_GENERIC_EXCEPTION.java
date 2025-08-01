package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.*;

public class CATCH_GENERIC_EXCEPTION {

    // Небезопасная конструкция: перехватывает все исключения обобщённо
    public static void unsafeHandle() {
        try {
            processFile();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так"); // Общее сообщение без деталей
        }
    }

    // Безопасная конструкция: перехватывает конкретные типы исключений
    public static void safeHandle() {
        try {
            processFile();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    // Метод, который выбрасывает исключение для демонстрации
    public static void processFile() throws IOException {
        // Имитация ошибки чтения
        throw new FileNotFoundException("Демонстрация: файл отсутствует");
    }

    public static void main(String[] args) {
        System.out.println("Небезопасная обработка:");
        unsafeHandle();

        System.out.println("\nБезопасная обработка:");
        safeHandle();
    }
}

