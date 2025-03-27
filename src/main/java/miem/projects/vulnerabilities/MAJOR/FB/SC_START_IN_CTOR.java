package miem.projects.vulnerabilities.MAJOR.FB;

public class SC_START_IN_CTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Task {
            private final Thread thread;

            public Task() {
                thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
                thread.start(); // поток запускается в конструкторе
            }
        }

        new Task(); // запустится до завершения конструктора
    }

    public static void correctTest() {
        class Task {
            private final Thread thread;

            public Task() {
                thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
            }

            public void start() {
                thread.start(); // запускается явно, после создания объекта
            }
        }

        Task task = new Task();
        task.start(); // запустится после завершения конструктора
    }
}
