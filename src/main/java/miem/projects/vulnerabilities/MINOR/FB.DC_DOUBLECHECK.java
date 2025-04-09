package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DC_DOUBLECHECK {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректная реализация double-checked locking
    public static class SingletonIncorrect {
        private static SingletonIncorrect instance;
        
        public static SingletonIncorrect getInstance() {
            if (instance == null) {  // Первая проверка (не синхронизирована)
                synchronized (SingletonIncorrect.class) {
                    if (instance == null) {  // Вторая проверка
                        instance = new SingletonIncorrect();
                    }
                }
            }
            return instance;
        }
        
        private SingletonIncorrect() {}
    }

    // Корректная реализация с volatile
    public static class SingletonCorrect {
        private static volatile SingletonCorrect instance;
        
        public static SingletonCorrect getInstance() {
            SingletonCorrect result = instance;
            if (result == null) {
                synchronized (SingletonCorrect.class) {
                    result = instance;
                    if (result == null) {
                        instance = result = new SingletonCorrect();
                    }
                }
            }
            return result;
        }
        
        private SingletonCorrect() {}
    }

    // Альтернативная корректная реализация (через holder class)
    public static class SingletonBest {
        private static class Holder {
            static final SingletonBest INSTANCE = new SingletonBest();
        }
        
        public static SingletonBest getInstance() {
            return Holder.INSTANCE;
        }
        
        private SingletonBest() {}
    }

    public static void incorrectTest() {
        new Thread(() -> {
            SingletonIncorrect instance = SingletonIncorrect.getInstance();
            System.out.println("Thread 1 instance: " + instance);
        }).start();
        
        new Thread(() -> {
            SingletonIncorrect instance = SingletonIncorrect.getInstance();
            System.out.println("Thread 2 instance: " + instance);
        }).start();
    }

    public static void correctTest() {
        new Thread(() -> {
            SingletonCorrect instance = SingletonCorrect.getInstance();
            System.out.println("Thread 1 correct instance: " + instance);
        }).start();
        
        new Thread(() -> {
            SingletonBest instance = SingletonBest.getInstance();
            System.out.println("Thread 2 best instance: " + instance);
        }).start();
    }
}
