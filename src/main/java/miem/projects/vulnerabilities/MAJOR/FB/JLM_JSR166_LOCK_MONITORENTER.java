package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JLM_JSR166_LOCK_MONITORENTER {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private final Lock lock = new ReentrantLock();

            public void method() {
                // синхронизация на объекте Lock
                synchronized (lock) {
                    System.out.println("Executing critical section");
                }
            }
        }
    }

    public static void correctTest() {
        class Test {
            private final Lock lock = new ReentrantLock();

            public void method() {
                // явная блокировка
                lock.lock();
                try {
                    // ...
                    System.out.println("Executing critical section");
                } finally {
                    // разблокировка
                    lock.unlock();
                }
            }
        }
    }
}
