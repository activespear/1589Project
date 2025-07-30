package miem.projects.vulnerabilities.MAJOR_1st;

public class DEADLOCK_CLASS_LOADING {

    // Небезопасная конструкция: может вызвать deadlock при инициализации классов
    public static void unsafe() {
        System.out.println("=== Небезопасная загрузка классов ===");
        try {
            Class.forName("DeadlockParent");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Безопасная конструкция: явная инициализация без циклической зависимости
    public static void safe() {
        System.out.println("=== Безопасная загрузка классов ===");
        SafeParent.init();
    }

    public static void main(String[] args) {
        unsafe();
        System.out.println();
        safe();
    }
}

// Небезопасные классы
class DeadlockParent {
    static {
        DeadlockSub.doSomething();  // Рекурсивная инициализация через Sub
    }
}

class DeadlockSub extends DeadlockParent {
    static void doSomething() {
        System.out.println("Sub method (unsafe)");
    }
}

// Безопасные классы
class SafeParent {
    static void init() {
        SafeSub.doSomething();
    }
}

class SafeSub extends SafeParent {
    static void doSomething() {
        System.out.println("Sub method (safe)");
    }
}
