package miem.projects.vulnerabilities.CRITICAL;

import java.util.Scanner;

public class TAINTED_ARRAY_INDEX_EX {

    // Метод для чтения индекса с верхней границей
    public static int boundedGetchar(int bound) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число (bound = " + bound + "): ");
        int res = scanner.nextInt();
        return res > bound ? bound : res;
    }

    // Небезопасная версия: неправильная верхняя граница, может выйти за пределы массива
    public static void arrayIndexError(int bound) {
        int[] buf = new int[256];
        int index = boundedGetchar(bound > 256 ? 256 : bound);

        System.out.println("Небезопасный индекс: " + index);
        if (index >= 0) {
            buf[index] = 7; // Возможен выход за границы, если index == 256
            System.out.println("Записано 7 в buf[" + index + "]");
        } else {
            System.out.println("Индекс отрицательный");
        }
    }

    // Безопасная версия: правильная верхняя граница 255 (последний индекс массива)
    public static void arrayIndexSafe(int bound) {
        int[] buf = new int[256];
        int index = boundedGetchar(bound > 255 ? 255 : bound);

        System.out.println("Безопасный индекс: " + index);
        if (index >= 0) {
            buf[index] = 7;
            System.out.println("Записано 7 в buf[" + index + "]");
        } else {
            System.out.println("Индекс отрицательный");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("=== Запуск небезопасной версии ===");
            arrayIndexError(256);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение в небезопасной версии: " + e);
        }

        System.out.println("\n=== Запуск безопасной версии ===");
        arrayIndexSafe(255);
    }
}

