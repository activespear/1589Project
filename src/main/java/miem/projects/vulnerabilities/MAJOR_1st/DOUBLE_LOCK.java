package miem.projects.vulnerabilities.MAJOR_1st;

public class DOUBLE_LOCK {

    private final Object lock = new Object();

    // Небезопасная функция с двойной блокировкой одного объекта
    public void unsafeDoubleLock() {
        synchronized (lock) {
            synchronized (lock) {
                System.out.println("Внутри небезопасной двойной блокировки");
                // Тут потенциальная проблема: может привести к логическим ошибкам или дедлоку в сложных сценариях
            }
        }
    }

    // Безопасная функция с однократной блокировкой
    public void safeLock() {
        synchronized (lock) {
            System.out.println("Внутри безопасной блокировки");
            // Работа с защищённым ресурсом
        }
    }

    // Метод запуска небезопасной функции
    public void runUnsafe() {
        System.out.println("Запуск небезопасной функции:");
        unsafeDoubleLock();
    }

    // Метод запуска безопасной функции
    public void runSafe() {
        System.out.println("Запуск безопасной функции:");
        safeLock();
    }

    public static void main(String[] args) {
        DOUBLE_LOCK example = new DOUBLE_LOCK();
        example.runUnsafe();
        System.out.println();
        example.runSafe();
    }
}
