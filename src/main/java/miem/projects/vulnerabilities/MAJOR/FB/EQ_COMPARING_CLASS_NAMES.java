package miem.projects.vulnerabilities.MAJOR.FB;

public class EQ_COMPARING_CLASS_NAMES {

    // Небезопасная версия MyClass
    static class UnsafeMyClass {
        private String name;

        public UnsafeMyClass(String name) {
            this.name = name;
        }

        // Небезопасный equals: сравнивает имена классов, а не значения полей
        public boolean unsafeEquals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            UnsafeMyClass other = (UnsafeMyClass) obj;
            return this.getClass().getName().equals(other.getClass().getName());
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    // Безопасная версия MyClass
    static class SafeMyClass {
        private String name;

        public SafeMyClass(String name) {
            this.name = name;
        }

        // Безопасный equals: сравнивает реальные значения name
        public boolean safeEquals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SafeMyClass other = (SafeMyClass) obj;
            return name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    public static void testUnsafe() {
        UnsafeMyClass a = new UnsafeMyClass("Alice");
        UnsafeMyClass b = new UnsafeMyClass("Alice");
        UnsafeMyClass c = new UnsafeMyClass("Bob");

        System.out.println("Unsafe equals a vs b: " + a.unsafeEquals(b)); // true, но не по значению
        System.out.println("Unsafe equals a vs c: " + a.unsafeEquals(c)); // true, так как сравнивает классы
    }

    public static void testSafe() {
        SafeMyClass a = new SafeMyClass("Alice");
        SafeMyClass b = new SafeMyClass("Alice");
        SafeMyClass c = new SafeMyClass("Bob");

        System.out.println("Safe equals a vs b: " + a.safeEquals(b)); // true, сравнение по значению
        System.out.println("Safe equals a vs c: " + a.safeEquals(c)); // false
    }

    public static void main(String[] args) {
        testUnsafe();
        testSafe();
    }
}

