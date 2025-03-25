package miem.projects.vulnerabilities.MAJOR.FB;

public class UMAC_UNCALLABLE_METHOD_OF_ANONYMOUS_CLASS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // ...
            }

            void execute() {
                // нигде не вызывается и
                // не переопределяет метод суперкласса
            }
        };
    }

    public static void correctTest() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                execute();
                // ...
            }

            void execute() {
                // ...
            }
        };
    }
}