package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.concurrent.atomic.AtomicBoolean;

public class JLM_JSR166_UTILCONCURRENT_MONITORENTER {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Test {
            private final AtomicBoolean lock = new AtomicBoolean(false);

            public void method() {
                // синхронизация на AtomicBoolean
                synchronized (lock) {
                    System.out.println("Executing critical section");
                }
            }
        }
    }

    public static void correctTest() {
        class Test {
            private final AtomicBoolean lock = new AtomicBoolean(false);

            public void method() {
                // Не используем синхронизацию, так как AtomicBoolean уже управляет параллелизмом
                if (lock.compareAndSet(false, true)) {
                    try {
                        // ...
                        System.out.println("Executing critical section");
                    } finally {
                        lock.set(false); // Обновление по завершении
                    }
                }
            }
        }
    }
}
