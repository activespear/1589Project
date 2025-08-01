package miem.projects.vulnerabilities.MAJOR.FB;

public class NP_TOSTRING_COULD_RETURN_NULL {

    static class UnsafeClass {
        private String name;

        public UnsafeClass(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            if (name == null) return null;  // Небезопасно возвращать null из toString()
            return name;
        }
    }

    static class SafeClass {
        private String name;

        public SafeClass(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name != null ? name : "";  // Безопасно возвращать пустую строку вместо null
        }
    }

    static void runUnsafe() {
        UnsafeClass obj1 = new UnsafeClass(null);
        System.out.println("Unsafe toString() output: " + obj1.toString());
    }

    static void runSafe() {
        SafeClass obj2 = new SafeClass(null);
        System.out.println("Safe toString() output: " + obj2.toString());
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe toString:");
        runUnsafe();

        System.out.println("\nRunning safe toString:");
        runSafe();
    }
}

