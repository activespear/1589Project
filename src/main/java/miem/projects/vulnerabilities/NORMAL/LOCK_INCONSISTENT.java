package miem.projects.vulnerabilities.NORMAL;

public class LOCK_INCONSISTENT  {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // ❌ Неправильная реализация: без volatile возможны проблемы с публикацией объекта
    public static void incorrectTest() {
        class DoubleCheckedLockingExample {
            private static class A { }

            private A aRef;

            public A brokenIdiom() {
                if (aRef == null) {                 // Первая проверка
                    synchronized (this) {
                        if (aRef == null) {         // Вторая проверка
                            aRef = new A();         // Не гарантируется корректная публикация
                        }
                    }
                }
                return aRef;
            }
        }

        DoubleCheckedLockingExample example = new DoubleCheckedLockingExample();
        example.brokenIdiom();
    }

    // ✅ Правильная реализация: поле объявлено как volatile
    public static void correctTest() {
        class DoubleCheckedLockingExample {
            private static class A { }

            private volatile A aRef;  // volatile обеспечивает корректную публикацию

            public A getInstance() {
                if (aRef == null) {                  // Первая проверка (без блокировки)
                    synchronized (this) {
                        if (aRef == null) {          // Вторая проверка (под блокировкой)
                            aRef = new A();          // Потокобезопасная инициализация
                        }
                    }
                }
                return aRef;
            }
        }

        DoubleCheckedLockingExample example = new DoubleCheckedLockingExample();
        example.getInstance();
    }
}

