package miem.projects.vulnerabilities.MAJOR_1st;

public class BUFFER_OVERFLOW_PROC_STRICT {

    // Небезопасная функция — может вызвать ArrayIndexOutOfBoundsException
    public static void unsafeFill(char[] p, int len) {
        for (int i = 0; i < len; i++) {
            p[i] = 'a'; // Потенциальное переполнение массива
        }
    }

    // Безопасная функция — защищает от переполнения
    public static void safeFill(char[] p, int len) {
        int limit = Math.min(len, p.length);
        for (int i = 0; i < limit; i++) {
            p[i] = 'a'; // Только в пределах массива
        }
    }

    public static void main(String[] args) {
        char[] buffer = new char[50];

        // Попытка небезопасного вызова
        try {
            System.out.println("Вызов unsafeFill(buffer, 1000)...");
            unsafeFill(buffer, 1000); // Ошибка: выход за пределы массива
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: переполнение буфера в unsafeFill!");
        }

        // Безопасный вызов
        System.out.println("Вызов safeFill(buffer, 1000)...");
        safeFill(buffer, 1000); // Работает корректно

        System.out.println("Вызов safeFill(buffer, 30)...");
        safeFill(buffer, 30); // Работает корректно
    }
}

