package miem.projects.vulnerabilities.CRITICAL;

import java.util.Scanner;

public class TAINTED_ARRAY_INDEX {

    // Небезопасная версия: проверка только сверху, без проверки на отрицательные индексы
    public static void unsafeArrayIndex() {
        int[] buf = new int[256];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите индекс (небезопасный метод): ");
        int index = scanner.nextInt();

        if (index < 256) {  // Нет проверки index >= 0
            buf[index] = 7; // Может вызвать ArrayIndexOutOfBoundsException, если index < 0
            System.out.println("Записано 7 в buf[" + index + "]");
        } else {
            System.out.println("Индекс слишком большой");
        }
    }

    // Безопасная версия: проверка на нижнюю и верхнюю границы
    public static void safeArrayIndex() {
        int[] buf = new int[256];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите индекс (безопасный метод): ");
        int index = scanner.nextInt();

        if (index >= 0 && index < 256) {
            buf[index] = 7;
            System.out.println("Записано 7 в buf[" + index + "]");
        } else {
            System.out.println("Индекс вне диапазона");
        }
    }

    public static void main(String[] args) {
        try {
            unsafeArrayIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение в небезопасном методе: " + e);
        }

        safeArrayIndex();
    }
}
