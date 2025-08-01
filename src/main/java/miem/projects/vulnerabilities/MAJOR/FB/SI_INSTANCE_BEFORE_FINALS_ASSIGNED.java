package miem.projects.vulnerabilities.MAJOR.FB;

public class SI_INSTANCE_BEFORE_FINALS_ASSIGNED {

    // Небезопасная конструкция: static final поле инициализируется в static блоке
    static class Unsafe {
        static final String name;
        static {
            name = "MyClass";
            System.out.println("Unsafe static block executed, name set to: " + name);
        }
    }

    // Безопасная конструкция: static final поле инициализируется при объявлении
    static class Safe {
        static final String name = "MyClass";
        static {
            System.out.println("Safe static block executed, name is: " + name);
        }
    }

    // Запуск небезопасной конструкции
    public static void unsafeTest() {
        System.out.println("Accessing Unsafe.name: " + Unsafe.name);
    }

    // Запуск безопасной конструкции
    public static void safeTest() {
        System.out.println("Accessing Safe.name: " + Safe.name);
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe test ===");
        unsafeTest();

        System.out.println("\n=== Safe test ===");
        safeTest();
    }
}
