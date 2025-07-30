package miem.projects.vulnerabilities.MAJOR_1st;

public class BAD_WAIT_OF_COND {
    private final Object lock = new Object();
    private boolean ready = false;

    // Небезопасная реализация: использует if вместо while
    public void unsafeWait() throws InterruptedException {
        synchronized (lock) {
            if (!ready) {
                lock.wait(); // Может "проснуться" ложным образом (spurious wakeup)
            }
            System.out.println("unsafeWait: Готово к работе");
        }
    }

    // Безопасная реализация: использует while для повторной проверки условия
    public void safeWait() throws InterruptedException {
        synchronized (lock) {
            while (!ready) {
                lock.wait(); // Гарантированная повторная проверка
            }
            System.out.println("safeWait: Готово к работе");
        }
    }

    // Метод, устанавливающий флаг и уведомляющий
    public void signalReady() {
        synchronized (lock) {
            ready = true;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BAD_WAIT_OF_COND example = new BAD_WAIT_OF_COND();

        // Поток, который будет вызывать unsafeWait
        Thread unsafeThread = new Thread(() -> {
            try {
                example.unsafeWait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Поток, который будет вызывать safeWait
        Thread safeThread = new Thread(() -> {
            try {
                example.safeWait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        unsafeThread.start();
        safeThread.start();

        Thread.sleep(100); // Имитация задержки

        example.signalReady(); // Устанавливаем ready = true и уведомляем

        unsafeThread.join();
        safeThread.join();
    }
}

