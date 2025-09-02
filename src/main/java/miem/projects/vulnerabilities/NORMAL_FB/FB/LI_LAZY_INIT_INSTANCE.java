package miem.projects.vulnerabilities.NORMAL.FB;

public class LI_LAZY_INIT_INSTANCE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class UnsafeSingleton {
            private static Singleton instance;

            public static Singleton getInstance() {
                if (instance == null) {
                    instance = new Singleton();
                }
                return instance;
            }
        }

        Singleton obj = UnsafeSingleton.getInstance();
        System.out.println("UnsafeSingleton instance: " + obj);
    }

    public static void correctTest() {
        class SafeSingleton {
            private static volatile Singleton instance;

            public static Singleton getInstance() {
                if (instance == null) {
                    synchronized (SafeSingleton.class) {
                        if (instance == null) {
                            instance = new Singleton();
                        }
                    }
                }
                return instance;
            }
        }

        Singleton obj = SafeSingleton.getInstance();
        System.out.println("SafeSingleton instance: " + obj);
    }

    // Простейший класс Singleton для примера
    static class Singleton {
    }
}
