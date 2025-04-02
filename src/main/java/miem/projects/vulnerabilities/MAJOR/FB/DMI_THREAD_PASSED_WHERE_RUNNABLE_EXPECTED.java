package miem.projects.vulnerabilities.MAJOR.FB;

public class DMI_THREAD_PASSED_WHERE_RUNNABLE_EXPECTED {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Thread thread = new Thread(() -> System.out.println("Thread running"));
        // Передаем Thread вместо Runnable
        Thread newThread = new Thread(thread);
        newThread.start();
    }

    public static void correctTest() {
        Runnable runnable = () -> System.out.println("Thread running correctly");
        // Передаем корректный Runnable
        Thread newThread = new Thread(runnable);
        newThread.start();
    }
}