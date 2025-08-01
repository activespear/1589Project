package miem.projects.vulnerabilities.MAJOR_1st;

public class WRONG_ARGUMENTS_ORDER {

    // Небезопасная версия с неверным порядком аргументов
    public static void transferUnsafe(String from, String to, int amount) {
        System.out.printf("Transferring %d from %s to %s (unsafe call)%n", amount, from, to);
        // Тут могла бы быть логика перевода денег
    }

    // Безопасная версия с правильным порядком аргументов и вызовом
    public static void transferSafe(String sender, String recipient, int amount) {
        System.out.printf("Transferring %d from %s to %s (safe call)%n", amount, sender, recipient);
        // Тут могла бы быть логика перевода денег
    }

    public static void runUnsafe() {
        System.out.println("Running unsafe transfer:");
        // Аргументы перепутаны — перевод фактически идет от userB к userA, что может быть ошибкой
        transferUnsafe("userB", "userA", 100);
    }

    public static void runSafe() {
        System.out.println("Running safe transfer:");
        // Правильный порядок аргументов — перевод от userA к userB
        transferSafe("userA", "userB", 100);
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

