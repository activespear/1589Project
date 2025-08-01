package miem.projects.vulnerabilities.MAJOR.FB;

public class ICAST_INT_2_LONG_AS_INSTANT {

    // Небезопасная конструкция: приведение int к long без проверки диапазона
    public static void runUnsafe() {
        int intTime = Integer.MAX_VALUE;  // 2147483647, может быть ошибочно воспринят как миллисекунды
        long longTime = (long) intTime;   // Приведение без валидации
        processTime(longTime);
    }

    // Безопасная конструкция: проверка диапазона перед приведением
    public static void runSafe() {
        int intTime = Integer.MAX_VALUE;

        // Проверка того, что значение в допустимом и ожидаемом диапазоне
        if (intTime > 0 && intTime <= Long.MAX_VALUE) {
            long longTime = (long) intTime;
            processTime(longTime);
        } else {
            System.out.println("Invalid time value.");
        }
    }

    public static void processTime(long time) {
        System.out.println("Processing time: " + time);
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe:");
        runUnsafe();
        System.out.println();

        System.out.println("Running safe:");
        runSafe();
    }
}

