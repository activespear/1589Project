package miem.projects.vulnerabilities.CRITICAL;

import java.util.concurrent.locks.ReentrantLock;

public class FB_UL_UNRELEASED_LOCK_EXCEPTION_PATH {

    // Небезопасная реализация: lock.unlock() не вызывается при исключении
    public static void unsafeLockUsage() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println("Небезопасный метод: блокировка установлена");
        if (Math.random() > 0.5) {
            throw new RuntimeException("Исключение в небезопасном методе (unlock не вызван)");
        }
        lock.unlock(); // Может не выполниться
        System.out.println("Небезопасный метод: блокировка снята");
    }

    // Безопасная реализация: блокировка гарантированно снимается
    public static void safeLockUsage() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println("Безопасный метод: блокировка установлена");
        try {
            if (Math.random() > 0.5) {
                throw new RuntimeException("Исключение в безопасном методе (unlock будет вызван)");
            }
        } finally {
            lock.unlock();
            System.out.println("Безопасный метод: блокировка снята");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Безопасная конструкция ===");
        try {
            safeLockUsage();
        } catch (RuntimeException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }

        System.out.println("\n=== Небезопасная конструкция ===");
        try {
            unsafeLockUsage();
        } catch (RuntimeException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
        }
    }
}

