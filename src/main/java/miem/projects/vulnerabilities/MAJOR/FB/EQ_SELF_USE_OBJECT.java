package miem.projects.vulnerabilities.MAJOR.FB;

public class EQ_SELF_USE_OBJECT {

    // Небезопасная версия класса A с методом equals(A other)
    static class UnsafeA {
        private String name;

        public UnsafeA(String name) {
            this.name = name;
        }

        public boolean unsafeEquals(UnsafeA other) {
            if (this == other) return true;
            if (other == null) return false;
            return name.equals(other.name);
        }
    }

    // Небезопасная версия класса B, без переопределения equals
    static class UnsafeB extends UnsafeA {
        private int age;

        public UnsafeB(String name, int age) {
            super(name);
            this.age = age;
        }
    }

    // Безопасная версия класса A с переопределением equals(Object obj)
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

    // Безопасная версия класса B с корректным переопределением equals(Object obj)
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
        UnsafeA a1 = new UnsafeA("John");
        UnsafeA a2 = new UnsafeA("John");
        UnsafeB b = new UnsafeB("John", 30);

        System.out.println("Unsafe a1.unsafeEquals(a2): " + a1.unsafeEquals(a2)); // true
        // Небезопасно, так как UnsafeB не переопределяет equals и equals с UnsafeA работает только с UnsafeA
        // Следующая проверка вызовет ошибку компиляции, так как метод unsafeEquals принимает UnsafeA
        // System.out.println("Unsafe a1.unsafeEquals(b): " + a1.unsafeEquals(b)); // Ошибка

        // Можно привести к UnsafeA, но это опасно
        System.out.println("Unsafe a1.unsafeEquals((UnsafeA) b): " + a1.unsafeEquals((UnsafeA) b)); // true - неверно
    }

    public static void testSafe() {
        SafeA a1 = new SafeA("John");
        SafeA a2 = new SafeA("John");
        SafeB b1 = new SafeB("John", 30);
        SafeB b2 = new SafeB("John", 30);

        System.out.println("Safe a1.equals(a2): " + a1.equals(a2)); // true
        System.out.println("Safe a1.equals(b1): " + a1.equals(b1)); // false - разные классы
        System.out.println("Safe b1.equals(b2): " + b1.equals(b2)); // true
        System.out.println("Safe b1.equals(a1): " + b1.equals(a1)); // false - симметрично
    }

    public static void main(String[] args) {
        System.out.println("Testing unsafe equals:");
        testUnsafe();
        System.out.println("\nTesting safe equals:");
        testSafe();
    }
}

