package miem.projects.vulnerabilities.MAJOR_1st;

public class NO_LOCK_STAT {

    // Небезопасная версия: increment синхронизирован, getValue — нет
    static class UnsafeExample {
        private int counter;

        public synchronized void increment() {
            counter++;
        }

        public int getValue() {
            return counter;
        }
    }

    // Безопасная версия: оба метода синхронизированы
    static class SafeExample {
        private int counter;

        public synchronized void increment() {
            counter++;
        }

        public synchronized int getValue() {
            return counter;
        }
    }

    public static void main(String[] args) {
        UnsafeExample unsafe = new UnsafeExample();
        SafeExample safe = new SafeExample();

        // Тестируем UnsafeExample
        unsafe.increment();
        unsafe.increment();
        System.out.println("Unsafe counter: " + unsafe.getValue());

        // Тестируем SafeExample
        safe.increment();
        safe.increment();
        System.out.println("Safe counter: " + safe.getValue());
    }
}

