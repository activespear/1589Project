package miem.projects.vulnerabilities.CRITICAL;

public class OVERFLOW_UNDER_CHECK_LIB {

    // Небезопасная версия — без проверки индекса и длины строки
    public static void unsafeFunc(int i, String p) {
        char[] buf = new char[100];
        System.out.println("Небезопасная версия с i = " + i);
        if (i < 200) {
            // Может выбросить StringIndexOutOfBoundsException
            p.getChars(0, i, buf, 0);
            System.out.println("Небезопасная версия: копирование прошло успешно");
        }
    }

    // Безопасная версия — проверка индекса и длины строки
    public static void safeFunc(int i, String p) {
        char[] buf = new char[100];
        System.out.println("Безопасная версия с i = " + i);
        if (i >= 0 && i < buf.length && i <= p.length()) {
            p.getChars(0, i, buf, 0);
            System.out.println("Безопасная версия: копирование прошло успешно");
        } else {
            System.out.println("Безопасная версия: условие не выполнено, копирование пропущено");
        }
    }

    public static void main(String[] args) {
        String testStr = "Hello, this is a test string!";

        System.out.println("=== Безопасная конструкция ===");
        safeFunc(10, testStr);
        safeFunc(150, testStr);  // не выполнится, т.к. i >= buf.length или i > p.length()

        System.out.println("\n=== Небезопасная конструкция ===");
        try {
            unsafeFunc(10, testStr);
            unsafeFunc(150, testStr);  // вызовет исключение
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение в небезопасной функции: " + e);
        }
    }
}

