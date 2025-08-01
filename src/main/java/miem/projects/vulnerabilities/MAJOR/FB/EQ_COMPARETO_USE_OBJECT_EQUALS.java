package miem.projects.vulnerabilities.MAJOR.FB;

public class EQ_COMPARETO_USE_OBJECT_EQUALS {

    // Небезопасная версия: только compareTo, equals и hashCode не переопределены
    static class UnsafePerson implements Comparable<UnsafePerson> {
        private String name;

        public UnsafePerson(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(UnsafePerson o) {
            return this.name.compareTo(o.name);
        }
    }

    // Безопасная версия: переопределены equals и hashCode, согласованные с compareTo
    static class SafePerson implements Comparable<SafePerson> {
        private String name;

        public SafePerson(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(SafePerson o) {
            return this.name.compareTo(o.name);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SafePerson other = (SafePerson) obj;
            return name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    // Функция запуска небезопасной конструкции
    public static void unsafeTest() {
        UnsafePerson p1 = new UnsafePerson("Alice");
        UnsafePerson p2 = new UnsafePerson("Alice");

        System.out.println("Unsafe compareTo result: " + p1.compareTo(p2));
        System.out.println("Unsafe equals result: " + p1.equals(p2)); // Object.equals, сравнение по ссылке
    }

    // Функция запуска безопасной конструкции
    public static void safeTest() {
        SafePerson p1 = new SafePerson("Alice");
        SafePerson p2 = new SafePerson("Alice");

        System.out.println("Safe compareTo result: " + p1.compareTo(p2));
        System.out.println("Safe equals result: " + p1.equals(p2)); // сравнение по значению
    }

    public static void main(String[] args) {
        unsafeTest();
        safeTest();
    }
}

