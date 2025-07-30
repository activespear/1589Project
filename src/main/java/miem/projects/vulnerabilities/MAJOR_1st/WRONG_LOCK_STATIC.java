package miem.projects.vulnerabilities.MAJOR_1st;

public class WRONG_LOCK_STATIC {

    static class UnsafeExample {
        private static int sharedCounter = 0;

        public void increment() {
            synchronized (this) { // НЕБЕЗОПАСНО: синхронизация по экземпляру, а поле статическое
                sharedCounter++;
                System.out.println("Unsafe increment: " + sharedCounter);
            }
        }
    }

    static class SafeExample {
        private static int sharedCounter = 0;

        public void increment() {
            synchronized (SafeExample.class) { // БЕЗОПАСНО: синхронизация по классу для статического поля
                sharedCounter++;
                System.out.println("Safe increment: " + sharedCounter);
            }
        }
    }

    public static void runUnsafe() {
        UnsafeExample example = new UnsafeExample();
        System.out.println("Running unsafe example:");
        example.increment();
    }

    public static void runSafe() {
        SafeExample example = new SafeExample();
        System.out.println("Running safe example:");
        example.increment();
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

