package miem.projects.vulnerabilities.MAJOR.FB;

public class FI_EXPLICIT_INVOCATION {

    // Небезопасная версия с явным вызовом finalize()
    static class UnsafeClass {
        private String resource;

        public UnsafeClass(String resource) {
            this.resource = resource;
        }

        public void cleanup() {
            try {
                finalize();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        @Override
        protected void finalize() {
            System.out.println("Cleaning up resource in finalize...");
        }
    }

    // Безопасная версия с AutoCloseable
    static class SafeClass implements AutoCloseable {
        private String resource;

        public SafeClass(String resource) {
            this.resource = resource;
        }

        @Override
        public void close() {
            System.out.println("Cleaning up resource in close...");
        }
    }

    // Запуск небезопасной версии
    public static void runUnsafe() {
        UnsafeClass obj = new UnsafeClass("unsafe_resource");
        System.out.println("Created unsafe object");
        obj.cleanup();
    }

    // Запуск безопасной версии
    public static void runSafe() {
        try (SafeClass obj = new SafeClass("safe_resource")) {
            System.out.println("Created safe object");
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("Running safe version:");
        runSafe();
    }
}

