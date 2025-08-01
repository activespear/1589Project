package miem.projects.vulnerabilities.MAJOR_1st;

import java.io.IOException;

public class INFINITE_LOOP_EX {

    // Метод, имитирующий операцию, которая может выбрасывать исключение
    static void riskyOperation(int i) throws IOException {
        if (i % 2 == 0) {
            throw new IOException("Ошибка при обработке " + i);
        }
        System.out.println("Обработано: " + i);
    }

    // Метод, имитирующий безопасную обработку исключений
    static void handle(IOException e) {
        System.out.println("Обработано исключение: " + e.getMessage());
    }

    // Небезопасная конструкция: может привести к бесконечному циклу
    public static void unsafeLoop() {
        int i = 0;
        while (i < 5) {
            try {
                riskyOperation(i);
                i++; // увеличивается только при отсутствии исключения
            } catch (IOException e) {
                System.out.println("Исключение без обработки: " + e.getMessage());
            }
        }
    }

    // Безопасная конструкция: i увеличивается в finally
    public static void safeLoop() {
        int i = 0;
        while (i < 5) {
            try {
                riskyOperation(i);
            } catch (IOException e) {
                handle(e);
            } finally {
                i++; // гарантированное увеличение
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Запуск небезопасной конструкции:");
        unsafeLoop();

        System.out.println("\nЗапуск безопасной конструкции:");
        safeLoop();
    }
}

