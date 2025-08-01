package miem.projects.vulnerabilities.MAJOR.FB;

public class FI_USELESS {

    static class SuperClass {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("SuperClass finalize called");
            super.finalize();
        }
    }

    // Небезопасная конструкция: переопределяет finalize, но только вызывает super.finalize()
    static class UnsafeSubClass extends SuperClass {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("UnsafeSubClass finalize called");
            super.finalize();
        }

        public void callFinalize() throws Throwable {
            finalize();
        }
    }

    // Безопасная конструкция: не переопределяет finalize вовсе
    static class SafeSubClass extends SuperClass {
        // ничего не делает — использует finalize из SuperClass
    }

    public static void runUnsafe() {
        UnsafeSubClass obj = new UnsafeSubClass();
        System.out.println("Created unsafe subclass object");
        try {
            obj.callFinalize();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void runSafe() {
        SafeSubClass obj = new SafeSubClass();
        System.out.println("Created safe subclass object");
        try {
            obj.finalize();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("Running safe version:");
        runSafe();
    }
}

