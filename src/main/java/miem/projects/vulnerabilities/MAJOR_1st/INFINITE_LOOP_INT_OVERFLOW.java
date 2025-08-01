package miem.projects.vulnerabilities.MAJOR_1st;

public class INFINITE_LOOP_INT_OVERFLOW {

    // Небезопасная конструкция: переполнение int вызывает бесконечный цикл
    public static void unsafeLoop() {
        System.out.println("Запуск небезопасной конструкции:");
        for (int i = Integer.MAX_VALUE - 1; i <= Integer.MAX_VALUE; i++) {
            System.out.println("i = " + i);
            if (i < 0) {
                System.out.println("Переполнение произошло, выход из цикла");
                break;
            }
        }
    }

    // Безопасная конструкция: использует long и ограниченный диапазон
    public static void safeLoop() {
        System.out.println("Запуск безопасной конструкции:");
        for (long i = 0; i < 10; i++) { // Уменьшено для демонстрации
            System.out.println("i = " + i);
        }
    }

    public static void main(String[] args) {
        unsafeLoop();
        safeLoop();
    }
}

