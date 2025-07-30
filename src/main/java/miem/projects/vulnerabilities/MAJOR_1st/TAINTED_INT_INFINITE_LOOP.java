package miem.projects.vulnerabilities.MAJOR_1st;

import java.util.Scanner;

public class TAINTED_INT_INFINITE_LOOP {

    // Небезопасная версия (без проверки шага)
    public static void unsafeLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Unsafe version - enter step: ");
        int step = scanner.nextInt();

        for (char i = 0; i < 100; i += step) {
            // Здесь могла бы быть логика в цикле
        }

        System.out.println("Unsafe loop completed.");
        // scanner.close(); // Не закрываем Scanner, чтобы можно было использовать во второй функции
    }

    // Безопасная версия (с проверкой шага)
    public static void safeLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Safe version - enter step: ");
        int step = scanner.nextInt();

        if (step <= 0) {
            System.out.println("Step must be greater than 0.");
            return;
        }

        for (char i = 0; i < 100; i += step) {
            // Здесь могла бы быть логика в цикле
        }

        System.out.println("Safe loop completed.");
        scanner.close();
    }

    public static void main(String[] args) {
        unsafeLoop();
        safeLoop();
    }
}
