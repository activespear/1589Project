package miem.projects.vulnerabilities.MAJOR_1st;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NO_UNLOCK {

    static class UnsafeExample {
        private final Lock lock = new ReentrantLock();
        private boolean error = false;

        void doWork() {
            System.out.println("Doing work");
        }

        void unsafeMethod() {
            lock.lock();
            if (error) return;  // unlock() не вызовется при error==true
            doWork();
            lock.unlock();
        }
    }

    static class SafeExample {
        private final Lock lock = new ReentrantLock();
        private boolean error = false;

        void doWork() {
            System.out.println("Doing work");
        }

        void safeMethod() {
            lock.lock();
            try {
                if (error) return;
                doWork();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        UnsafeExample unsafe = new UnsafeExample();
        unsafe.error = true;
        unsafe.unsafeMethod(); // lock останется захваченным при error == true

        SafeExample safe = new SafeExample();
        safe.error = true;
        safe.safeMethod(); // lock будет гарантированно освобождён
    }
}

