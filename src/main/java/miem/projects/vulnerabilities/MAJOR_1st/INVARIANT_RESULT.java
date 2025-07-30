package miem.projects.vulnerabilities.MAJOR_1st;

public class INVARIANT_RESULT {

    // Небезопасная версия: условие всегда истинно
    public static void unsafeCheck(int a) {
        if ((a & 0) == 0) {
            System.out.println("Always true");
        }
    }

    // Безопасная версия: проверяется конкретный флаг
    public static void safeCheck(int a) {
        int FLAG = 0x4;
        if ((a & FLAG) != 0) {
            System.out.println("FLAG is set");
        }
    }

    public static void main(String[] args) {
        System.out.println("Небезопасная проверка:");
        unsafeCheck(0);
        unsafeCheck(4);
        unsafeCheck(8);

        System.out.println("Безопасная проверка:");
        safeCheck(0);
        safeCheck(4);
        safeCheck(8);
    }
}

