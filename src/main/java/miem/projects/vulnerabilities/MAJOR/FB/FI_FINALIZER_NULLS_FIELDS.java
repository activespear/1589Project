package miem.projects.vulnerabilities.MAJOR.FB;

public class FI_FINALIZER_NULLS_FIELDS {

    // Небезопасная конструкция с finalize
    static class UnsafeClass {
        private String resource = "unsafe_resource";

        public UnsafeClass() {}

        @Override
        protected void finalize() throws Throwable {
            try {
                resource = null;
                System.out.println("Unsafe finalize: resource set to null");
            } finally {
                super.finalize();
            }
        }

        public void callFinalize() throws Throwable {
            finalize();
        }
    }

    // Безопасная конструкция с AutoCloseable
    static class SafeClass implements AutoCloseable {
        private String resource;

        public SafeClass(String resource) {
            this.resource = resource;
        }

        @Override
        public void close() {
            resource = null;
            System.out.println("Safe close: resource set to null");
        }
    }

    // Запуск небезопасной конструкции
    public static void runUnsafe() {
        UnsafeClass obj = new UnsafeClass();
        System.out.println("Created unsafe object");
        try {
            obj.callFinalize();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    // Запуск безопасной конструкции
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

