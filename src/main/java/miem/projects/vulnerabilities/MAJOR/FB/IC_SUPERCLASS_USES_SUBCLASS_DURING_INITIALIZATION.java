package miem.projects.vulnerabilities.MAJOR.FB;

public class IC_SUPERCLASS_USES_SUBCLASS_DURING_INITIALIZATION {

    // Небезопасная конструкция: суперкласс вызывает метод подкласса во время своей инициализации
    static class SuperUnsafe {
        static {
            System.out.println("SuperUnsafe static block");
            SubUnsafe.test(); // Опасно: SubUnsafe может быть ещё не инициализирован
        }
    }

    static class SubUnsafe extends SuperUnsafe {
        static void test() {
            System.out.println("Init SubUnsafe");
        }
    }

    // Безопасная конструкция: суперкласс не зависит от подкласса при инициализации
    static class SuperSafe {
        static void init() {
            System.out.println("SuperSafe init");
        }
    }

    static class SubSafe extends SuperSafe {
        static {
            init(); // безопасный вызов: метод в суперклассе
        }
    }

    public static void runUnsafe() {
        System.out.println("Running unsafe:");
        new SubUnsafe(); // Триггерит инициализацию SuperUnsafe
    }

    public static void runSafe() {
        System.out.println("Running safe:");
        new SubSafe(); // Безопасная инициализация
    }

    public static void main(String[] args) {
        runUnsafe();
        System.out.println();
        runSafe();
    }
}

