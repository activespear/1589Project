package miem.projects.vulnerabilities.MAJOR.FB;

public class STI_INTERRUPTED_ON_UNKNOWNTHREAD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Thread thread = new Thread(() -> {
            // ...
        });
        thread.start();
        if (thread.interrupted()) {
            // Thread was interrupted ...
        }
    }

    public static void correctTest() {
        Thread thread = new Thread(() -> {
            // ...
        });
        if (Thread.interrupted()) {
            // Thread was interrupted ...
        }
        thread.start();
        // Прерываем поток
        thread.interrupt();
    }
}
