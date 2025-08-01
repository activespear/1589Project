package miem.projects.vulnerabilities.MAJOR.FB;

public class BC_EQUALS_METHOD_SHOULD_WORK_FOR_ALL_OBJECTS {

    // Небезопасный класс с equals без проверок
    static class UnsafePerson {
        String name;

        UnsafePerson(String name) {
            this.name = name;
        }

        public boolean equals(Object o) {
            return name.equals(((UnsafePerson) o).name);
        }
    }

    // Безопасный класс с корректным equals и hashCode
    static class SafePerson {
        String name;

        SafePerson(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SafePerson)) return false;
            SafePerson other = (SafePerson) o;
            return name != null ? name.equals(other.name) : other.name == null;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    // Проверка небезопасного equals (может кинуть исключение)
    public static void unsafeEqualsTest() {
        UnsafePerson p1 = new UnsafePerson("Alice");
        UnsafePerson p2 = new UnsafePerson("Alice");
        UnsafePerson p3 = new UnsafePerson(null);

        System.out.println("Unsafe equals p1 vs p2: " + p1.equals(p2));

        try {
            System.out.println("Unsafe equals p1 vs p3: " + p1.equals(p3));
        } catch (NullPointerException e) {
            System.out.println("Unsafe equals threw NullPointerException when comparing with null name.");
        }

        try {
            System.out.println("Unsafe equals p1 vs String: " + p1.equals("test"));
        } catch (ClassCastException e) {
            System.out.println("Unsafe equals threw ClassCastException when comparing with different class.");
        }
    }

    // Проверка безопасного equals
    public static void safeEqualsTest() {
        SafePerson p1 = new SafePerson("Alice");
        SafePerson p2 = new SafePerson("Alice");
        SafePerson p3 = new SafePerson(null);

        System.out.println("Safe equals p1 vs p2: " + p1.equals(p2));
        System.out.println("Safe equals p1 vs p3: " + p1.equals(p3));
        System.out.println("Safe equals p3 vs null: " + p3.equals(null));
        System.out.println("Safe equals p1 vs String: " + p1.equals("test"));
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeEqualsTest:");
        unsafeEqualsTest();

        System.out.println("\nRunning safeEqualsTest:");
        safeEqualsTest();
    }
}

