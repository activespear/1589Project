package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HE_INHERITS_EQUALS_USE_HASHCODE {

    // Небезопасная конструкция: переопределён equals в суперклассе, но hashCode не переопределён
    abstract static class PersonUnsafe {
        protected String name;

        public PersonUnsafe(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PersonUnsafe p) {
                return Objects.equals(name, p.name);
            }
            return false;
        }

        // hashCode отсутствует
    }

    static class EmployeeUnsafe extends PersonUnsafe {
        private int id;

        public EmployeeUnsafe(String name, int id) {
            super(name);
            this.id = id;
        }
    }

    // Безопасная конструкция: подкласс дополняет hashCode
    static class PersonSafe {
        protected String name;

        public PersonSafe(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof PersonSafe p) {
                return Objects.equals(name, p.name);
            }
            return false;
        }
    }

    static class EmployeeSafe extends PersonSafe {
        private int id;

        public EmployeeSafe(String name, int id) {
            super(name);
            this.id = id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name); // согласован с equals
        }
    }

    public static void runUnsafe() {
        Set<EmployeeUnsafe> set = new HashSet<>();
        EmployeeUnsafe e1 = new EmployeeUnsafe("Alice", 1);
        EmployeeUnsafe e2 = new EmployeeUnsafe("Alice", 2);

        set.add(e1);
        System.out.println("Unsafe contains e2? " + set.contains(e2)); // false — hashCode отсутствует
    }

    public static void runSafe() {
        Set<EmployeeSafe> set = new HashSet<>();
        EmployeeSafe e1 = new EmployeeSafe("Alice", 1);
        EmployeeSafe e2 = new EmployeeSafe("Alice", 2);

        set.add(e1);
        System.out.println("Safe contains e2? " + set.contains(e2)); // true — hashCode определён
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("\nRunning safe version:");
        runSafe();
    }
}

