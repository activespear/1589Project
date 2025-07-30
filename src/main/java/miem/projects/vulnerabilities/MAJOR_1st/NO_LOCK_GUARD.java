package miem.projects.vulnerabilities.MAJOR_1st;

public class NO_LOCK_GUARD {

    // Небезопасный вариант: увеличивает count без блокировки
    static class Unsafe {
        private final Object lock = new Object();
        // @GuardedBy("lock")
        private int count;

        public void unsafeInc() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    // Безопасный вариант: увеличивает count с блокировкой
    static class Safe {
        private final Object lock = new Object();
        // @GuardedBy("lock")
        private int count;

        public void safeInc() {
            synchronized (lock) {
                count++;
            }
        }

        public int getCount() {
            synchronized (lock) {
                return count;
            }
        }
    }

    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        Safe safe = new Safe();

        // Запускаем небезопасный инкремент несколько раз
        for (int i = 0; i < 5; i++) {
            unsafe.unsafeInc();
        }
        System.out.println("Unsafe count: " + unsafe.getCount());

        // Запускаем безопасный инкремент несколько раз
        for (int i = 0; i < 5; i++) {
            safe.safeInc();
        }
        System.out.println("Safe count: " + safe.getCount());
    }
}
