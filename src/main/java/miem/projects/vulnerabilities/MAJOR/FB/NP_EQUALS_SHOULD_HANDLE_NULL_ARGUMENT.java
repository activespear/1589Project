package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Objects;

public class NP_EQUALS_SHOULD_HANDLE_NULL_ARGUMENT {

    static class UnsafeMyClass {
        private String field;

        public UnsafeMyClass(String field) {
            this.field = field;
        }

        // Небезопасный equals — не проверяет obj на null и класс
        @Override
        public boolean equals(Object obj) {
            // без проверки на null или instanceof
            UnsafeMyClass other = (UnsafeMyClass) obj; // Может бросить NullPointerException или ClassCastException
            return this.field.equals(other.field);
        }

        // Для отладки
        @Override
        public String toString() {
            return "UnsafeMyClass{" + "field='" + field + '\'' + '}';
        }
    }

    static class SafeMyClass {
        private String field;

        public SafeMyClass(String field) {
            this.field = field;
        }

        // Безопасный equals с проверкой null и instanceof
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof SafeMyClass)) return false;
            SafeMyClass other = (SafeMyClass) obj;
            return Objects.equals(this.field, other.field);
        }

        @Override
        public String toString() {
            return "SafeMyClass{" + "field='" + field + '\'' + '}';
        }
    }

    // Запуск небезопасного equals, может выбросить исключение
    static void runUnsafe() {
        UnsafeMyClass a = new UnsafeMyClass("test");
        Object b = null;
        System.out.println("Unsafe equals result: " + a.equals(b));  // Здесь будет NPE
    }

    // Запуск безопасного equals
    static void runSafe() {
        SafeMyClass a = new SafeMyClass("test");
        Object b = null;
        System.out.println("Safe equals result with null: " + a.equals(b));  // false
        SafeMyClass c = new SafeMyClass("test");
        System.out.println("Safe equals result same field: " + a.equals(c)); // true
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        try {
            runUnsafe();
        } catch (Exception e) {
            System.out.println("Caught exception in unsafe equals: " + e);
        }

        System.out.println("\nRunning safe version:");
        runSafe();
    }
}

