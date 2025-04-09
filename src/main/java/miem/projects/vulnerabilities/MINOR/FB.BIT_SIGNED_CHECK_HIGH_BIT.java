package miem.projects.vulnerabilities.MINOR.FB;

public class BIT_SIGNED_CHECK_HIGH_BIT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректная проверка старшего бита (знакового)
    public static void incorrectTest() {
        int value = -42;
        if ((value & 0x80) != 0) { // ОШИБКА: проверка только 8-го бита, а не знакового
            System.out.println("Negative number detected (INCORRECT)");
        } else {
            System.out.println("Positive number (INCORRECT)");
        }
    }

    // Корректная проверка знакового бита
    public static void correctTest() {
        int value = -42;
        if (value < 0) { // Правильный способ проверки знака
            System.out.println("Negative number detected (CORRECT)");
        } else {
            System.out.println("Positive number (CORRECT)");
        }
    }
}