package miem.projects.vulnerabilities.MAJOR.FB;

import java.net.URL;

public class UI_INHERITANCE_UNSAFE_GETRESOURCE {

    // Небезопасная конструкция: getResource вызывается через this.getClass()
    static class UnsafeBaseClass {
        public URL loadResource() {
            URL resource = this.getClass().getResource("config.properties");
            System.out.println("UnsafeBaseClass resource: " + resource);
            return resource;
        }
    }

    // Безопасная конструкция: getResource вызывается через BaseClass.class
    static class SafeBaseClass {
        public URL loadResource() {
            URL resource = SafeBaseClass.class.getResource("config.properties");
            System.out.println("SafeBaseClass resource: " + resource);
            return resource;
        }
    }

    // Запуск небезопасной конструкции
    public static void unsafeTest() {
        UnsafeBaseClass obj = new UnsafeBaseClass();
        obj.loadResource();
    }

    // Запуск безопасной конструкции
    public static void safeTest() {
        SafeBaseClass obj = new SafeBaseClass();
        obj.loadResource();
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe test ===");
        unsafeTest();

        System.out.println("\n=== Safe test ===");
        safeTest();
    }
}

