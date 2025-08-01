package miem.projects.vulnerabilities.CRITICAL;

public class FB_ML_SYNC_ON_FIELD_TO_GUARD_CHANGING_THAT_FIELD {

    // Небезопасный пример: объект lock может быть изменён во время использования
    static class UnsafeExample {
        private Object lock = new Object();

        public void method() {
            synchronized (lock) {
                System.out.println("Небезопасный метод: внутри synchronized блока");
            }
        }

        public void changeLock() {
            lock = new Object(); // Меняет объект блокировки, возможно во время использования
            System.out.println("Небезопасный метод: lock был изменён");
        }
    }

    // Безопасный пример: final гарантирует неизменность объекта lock
    static class SafeExample {
        private final Object lock = new Object();

        public void method() {
            synchronized (lock) {
                System.out.println("Безопасный метод: внутри synchronized блока");
            }
        }
    }

    // Запуск безопасной и небезопасной реализации
    public static void main(String[] args) {
        System.out.println("=== Безопасная конструкция ===");
        SafeExample safe = new SafeExample();
        safe.method();

        System.out.println("\n=== Небезопасная конструкция ===");
        UnsafeExample unsafe = new UnsafeExample();

        // Поток 1: вызывает synchronized метод
        Thread t1 = new Thread(() -> unsafe.method());

        // Поток 2: меняет объект блокировки
        Thread t2 = new Thread(() -> unsafe.changeLock());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван: " + e);
        }
    }
}

