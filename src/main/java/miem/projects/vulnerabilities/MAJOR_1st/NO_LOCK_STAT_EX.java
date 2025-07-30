package miem.projects.vulnerabilities.MAJOR_1st;

public class NO_LOCK_STAT_EX {

    // Небезопасная версия с методами-функциями
    static class UnsafeSharedData {
        private int value;

        public void update() {
            value++;
        }

        public int read() {
            return value;
        }
    }

    // Безопасная версия с методами-функциями
    static class SafeSharedData {
        private final Object lock = new Object();
        private int value;

        public void update() {
            synchronized (lock) {
                value++;
            }
        }

        public int read() {
            synchronized (lock) {
                return value;
            }
        }
    }

    public static void unsafeExample() {
        UnsafeSharedData data = new UnsafeSharedData();
        data.update();
        data.update();
        System.out.println("Unsafe value: " + data.read());
    }

    public static void safeExample() {
        SafeSharedData data = new SafeSharedData();
        data.update();
        data.update();
        System.out.println("Safe value: " + data.read());
    }

    public static void main(String[] args) {
        unsafeExample();
        safeExample();
    }
}
