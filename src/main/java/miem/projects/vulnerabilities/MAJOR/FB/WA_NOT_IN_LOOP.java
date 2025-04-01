package miem.projects.vulnerabilities.MAJOR.FB;

class SharedResourceIncorrect {
    private boolean ready = false;

    public synchronized void waitForReady() throws InterruptedException {
        wait(); // вызов wait() без цикла
        System.out.println("Condition met, proceeding...");
    }

    public synchronized void setReady() {
        ready = true;
        notifyAll();
    }
}

class SharedResourceCorrect {
    private boolean ready = false;

    public synchronized void waitForReady() throws InterruptedException {
        while (!ready) { // проверка условия в цикле
            wait();
        }
        System.out.println("Condition met, proceeding...");
    }

    public synchronized void setReady() {
        ready = true;
        notifyAll();
    }
}

public class WA_NOT_IN_LOOP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        SharedResourceIncorrect resource = new SharedResourceIncorrect();

        Thread waitingThread = new Thread(() -> {
            try {
                resource.waitForReady();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        waitingThread.start();

        try {
            Thread.sleep(1000); // Даем время потоку заснуть
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        resource.setReady(); // Пробуждаем поток
    }

    public static void correctTest() {
        SharedResourceCorrect resource = new SharedResourceCorrect();

        Thread waitingThread = new Thread(() -> {
            try {
                resource.waitForReady();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        waitingThread.start();

        try {
            Thread.sleep(1000); // Даем время потоку заснуть
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        resource.setReady(); // Пробуждаем поток
    }
}
