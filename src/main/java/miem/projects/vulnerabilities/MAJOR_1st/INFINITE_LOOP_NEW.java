package miem.projects.vulnerabilities.MAJOR_1st;

public class INFINITE_LOOP_NEW {

    // Небезопасная конструкция: бесконечный цикл, так как new int[10] всегда != null
    public static void unsafeLoop() {
        System.out.println("Запуск небезопасной конструкции:");
        int counter = 0;
        while (new int[10] != null) {
            if (counter++ > 5) {
                System.out.println("Прерывание бесконечного цикла для демонстрации.");
                break; // иначе бесконечно
            }
            System.out.println("Итерация " + counter);
        }
    }

    // Безопасная конструкция: условие изменяется и цикл завершится
    public static void safeLoop() {
        System.out.println("Запуск безопасной конструкции:");
        Object marker = new Object();
        Object sentinel = marker;
        while (marker == sentinel) {
            System.out.println("Один раз в безопасном цикле");
            marker = null;
        }
    }

    public static void main(String[] args) {
        unsafeLoop();
        safeLoop();
    }
}

