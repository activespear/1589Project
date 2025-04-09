package miem.projects.vulnerabilities.MINOR.FB;

public class NM_SAME_SIMPLE_NAME_AS_INTERFACE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректное именование класса
    public static void incorrectTest() {
        // ОШИБКА: класс имеет то же имя, что и интерфейс
        class List implements Runnable {
            @Override
            public void run() {
                System.out.println("Running...");
            }
        }
        new List().run();
    }

    // Корректное именование класса
    public static void correctTest() {
        class TaskList implements Runnable {
            @Override
            public void run() {
                System.out.println("Running task list...");
            }
        }
        new TaskList().run();
    }
}

// Интерфейс, имя которого конфликтует в incorrectTest()
interface Runnable {
    void run();
}