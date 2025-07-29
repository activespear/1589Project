package miem.projects.vulnerabilities.CRITICAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TAINTED_DYNAMIC_OVERFLOW {

    // Небезопасная версия — без проверки размера массива
    public static void unsafeFunc(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        System.out.println("Небезопасная версия: n = " + n);
        byte[] ptr = new byte[n];
        ptr[30] = 0;  // Возможен выход за границы, если n <= 30
        System.out.println("Записано 0 в ptr[30]");
        scanner.close();
    }

    // Безопасная версия — с проверкой n > 30 перед обращением к ptr[30]
    public static void safeFunc(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        System.out.println("Безопасная версия: n = " + n);
        if (n > 30) {
            byte[] ptr = new byte[n];
            ptr[30] = 0;
            System.out.println("Записано 0 в ptr[30]");
        } else {
            System.out.println("Размер n слишком мал, доступ к ptr[30] пропущен");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== Запуск небезопасной версии ===");
            unsafeFunc(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение выхода за границы массива в небезопасной версии: " + e);
        }

        try {
            System.out.println("\n=== Запуск безопасной версии ===");
            safeFunc(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e);
        }
    }
}

