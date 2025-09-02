package miem.projects.vulnerabilities.NORMAL;

public class NO_CHECK_IN_LOCK {
    public static void main(String[] args) {
        incorrectTest(42);
        correctTest(42);
    }

    //  Неправильная реализация: проверка флага выполняется вне блока synchronized
    public static void incorrectTest(int value) {
        class ClassA {
            private int x;
            ClassA(int xx) { x = xx; }
        }

        class ClassB {
            private final Object fLock = new Object();
            public ClassA fSharedObj;
            public boolean fCritialSection = false;

            public void access(int x) {
                // Проверка выполняется до входа в synchronized → условие может устареть
                if (fCritialSection) {
                    return;
                }

                synchronized (fLock) {
                    // Здесь флаг меняется, но его сравнение уже было "снаружи"
                    fCritialSection = true;
                }
                fSharedObj = new ClassA(x);
            }
        }

        ClassB obj = new ClassB();
        obj.access(value);
    }

    //  Правильная реализация: проверка флага выполняется внутри synchronized
    public static void correctTest(int value) {
        class ClassA {
            private int x;
            ClassA(int xx) { x = xx; }
        }

        class ClassB {
            private final Object fLock = new Object();
            private ClassA fSharedObj;
            private boolean fCritialSection = false;

            public void access(int x) {
                synchronized (fLock) {
                    // Проверка и изменение — атомарно в блоке synchronized
                    if (fCritialSection) {
                        return;
                    }
                    fCritialSection = true;
                }
                fSharedObj = new ClassA(x);
            }
        }

        ClassB obj = new ClassB();
        obj.access(value);
    }
}