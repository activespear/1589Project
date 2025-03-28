package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UL_UNRELEASED_LOCK {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private final Lock lock = new ReentrantLock();

            public void work(boolean fail) {
                lock.lock();
                try {
                    // ...
                    if (fail) {
                        throw new RuntimeException();
                    }
                } finally {
                    // Блокировка освобождается не во всех случаях
                    if (fail) {
                        lock.unlock();
                    }
                }
            }
        }
        Test test = new Test();
        test.work(false);
    }

    public static void correctTest() {
        class Test {
            private final Lock lock = new ReentrantLock();

            public void work(boolean fail) {
                lock.lock();
                try {
                    // ...
                    if (fail) {
                        throw new RuntimeException();
                    }
                } finally {
                    // Блокировка всегда освобождается
                    lock.unlock();
                }
            }
        }
        Test test = new Test();
        test.work(false);
    }
}
