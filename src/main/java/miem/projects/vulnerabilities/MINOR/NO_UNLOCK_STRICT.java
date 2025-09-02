package miem.projects.vulnerabilities.NORMAL;

import java.util.concurrent.locks.ReentrantLock;

public class NO_UNLOCK_STRICT {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        riskyMethod();
        safeMethod();
    }

    // Потенциально небезопасное: блокировка без разблокировки
    public static void riskyMethod() {
        lock.lock();
        System.out.println("Операция...");
    }

    // Корректная конструкция: всегда разблокируем в finally
    public static void safeMethod() {
        lock.lock();
        try {
            System.out.println("Операция...");
        } finally {
            lock.unlock();
        }
    }
}
