package miem.projects.vulnerabilities.MAJOR.FB;

public class EQ_OVERRIDING_EQUALS_NOT_SYMMETRIC {

    // Небезопасная версия класса A
    static class UnsafeA {
        private String name;

        public UnsafeA(String name) {
            this.name = name;
        }

        public boolean unsafeEquals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof UnsafeA) {
                UnsafeA other = (UnsafeA) obj;
                return name.equals(other.name);
            }
            return false;
        }
    }

    // Небезопасная версия класса B
    static class UnsafeB extends UnsafeA {
        private int age;

        public UnsafeB(String name, int age) {
            super(name);
            this.age = age;
        }

        public boolean unsafeEquals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof UnsafeB) {
                UnsafeB other = (UnsafeB) obj;
                return age == other.age;
            }
            return false;
        }
    }

    // Безопасная версия класса A
    static class SafeA {
        private String name;

        public SafeA(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SafeA other = (SafeA) obj;
            return name.equals(other.name);
        }
    }

    // Безопасная версия класса B
    static class SafeB extends SafeA {
        private int age;

        public SafeB(String name, int age) {
            super(name);
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SafeB other = (SafeB) obj;
            return age == other.age && super.equals(obj);
        }
    }

    public static void testUnsafe() {
        UnsafeA a = new UnsafeA("John");
        UnsafeB b = new UnsafeB("John", 30);

        System.out.println("Unsafe a.equals(b): " + a.unsafeEquals(b)); // true (потенциально)
        System.out.println("Unsafe b.equals(a): " + b.unsafeEquals(a)); // false - несимметрично
    }

    public static void testSafe() {
        SafeA a = new SafeA("John");
        SafeB b = new SafeB("John", 30);

        System.out.println("Safe a.equals(b): " + a.equals(b)); // false
        System.out.println("Safe b.equals(a): " + b.equals(a)); // false - симметрично
    }

    public static void main(String[] args) {
        testUnsafe();
        testSafe();
    }
}

