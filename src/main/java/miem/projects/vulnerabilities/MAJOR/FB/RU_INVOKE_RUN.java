package miem.projects.vulnerabilities.MAJOR.FB;

public class RU_INVOKE_RUN {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }

        Task task = new Task();
        // вызывается в текущем потоке, а не в новом
        task.run();
    }

    public static void correctTest() {
        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }

        Thread thread = new Thread(new Task());
        // создается новый поток
        thread.start();
    }
}
