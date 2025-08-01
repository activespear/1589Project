package miem.projects.vulnerabilities.MAJOR.FB;

public class FI_EMPTY {

    // Небезопасная версия с finalize()
    static class UnsafeClass {
        private String resource;

        public UnsafeClass(String resource) {
            this.resource = resource;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalize called for resource: " + resource);
            super.finalize();
        }
    }

    // Безопасная версия без finalize()
    static class SafeClass {
        private String resource;

        public SafeClass(String resource) {
            this.resource = resource;
        }
    }

    // Функция запускает небезопасную версию
    public static void runUnsafe() {
        UnsafeClass obj = new UnsafeClass("unsafe_resource");
        System.out.println("Created unsafe object");
        obj = null;
        System.gc(); // вызов сборщика мусора (не гарантирует вызов finalize)
        try {
            Thread.sleep(1000); // ждем немного, чтобы дать шанс finalize
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Функция запускает безопасную версию
    public static void runSafe() {
        SafeClass obj = new SafeClass("safe_resource");
        System.out.println("Created safe object");
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("Running safe version:");
        runSafe();
    }
}

