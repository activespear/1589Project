package miem.projects.vulnerabilities.MAJOR_1st;

import java.util.Scanner;

public class TAINTED_INT_INFINITE_LOOP_MIGHT {

    // Небезопасная версия (без проверки шага)
    public static void unsafeLoop() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Unsafe version - enter step: ");
        int step = sc.nextInt();

        for (int i = 0; i < 100; i += step) {
            // потенциально опасный цикл
        }

        System.out.println("Unsafe loop completed.");
        // Не закрываем Scanner здесь, чтобы использовать в safeLoop
    }

    // Безопасная версия (с проверкой шага)
    public static void safeLoop() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Safe version - enter step: ");
        int step = sc.nextInt();

        if (step <= 0 || step > 100) {
            step = 1;
        }

        for (int i = 0; i < 100; i += step) {
            // безопасный цикл
        }

        System.out.println("Safe loop completed.");
        sc.close();
    }

    public static void main(String[] args) {
        unsafeLoop();
        safeLoop();
    }
}

