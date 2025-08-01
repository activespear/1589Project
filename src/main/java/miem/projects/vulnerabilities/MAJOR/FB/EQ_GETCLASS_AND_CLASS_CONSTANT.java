package miem.projects.vulnerabilities.MAJOR.FB;

public class EQ_GETCLASS_AND_CLASS_CONSTANT {

    // Небезопасная версия Animal
    static class UnsafeAnimal {
        private String species;

        public UnsafeAnimal(String species) {
            this.species = species;
        }

        public boolean unsafeEquals(Object o) {
            if (o == null || UnsafeAnimal.class != o.getClass()) return false;
            UnsafeAnimal other = (UnsafeAnimal) o;
            return species.equals(other.species);
        }

        @Override
        public int hashCode() {
            return species.hashCode();
        }
    }

    // Безопасная версия Animal
    static class SafeAnimal {
        private String species;

        public SafeAnimal(String species) {
            this.species = species;
        }

        public boolean safeEquals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            SafeAnimal other = (SafeAnimal) o;
            return species.equals(other.species);
        }

        @Override
        public int hashCode() {
            return species.hashCode();
        }
    }

    public static void testUnsafe() {
        UnsafeAnimal a1 = new UnsafeAnimal("Dog");
        UnsafeAnimal a2 = new UnsafeAnimal("Dog");
        UnsafeAnimal a3 = new UnsafeAnimal("Cat");

        System.out.println("Unsafe equals a1 vs a2: " + a1.unsafeEquals(a2)); // true
        System.out.println("Unsafe equals a1 vs a3: " + a1.unsafeEquals(a3)); // false
        System.out.println("Unsafe equals a1 vs null: " + a1.unsafeEquals(null)); // false
    }

    public static void testSafe() {
        SafeAnimal a1 = new SafeAnimal("Dog");
        SafeAnimal a2 = new SafeAnimal("Dog");
        SafeAnimal a3 = new SafeAnimal("Cat");

        System.out.println("Safe equals a1 vs a2: " + a1.safeEquals(a2)); // true
        System.out.println("Safe equals a1 vs a3: " + a1.safeEquals(a3)); // false
        System.out.println("Safe equals a1 vs null: " + a1.safeEquals(null)); // false
        System.out.println("Safe equals a1 vs a1: " + a1.safeEquals(a1)); // true (this == o)
    }

    public static void main(String[] args) {
        testUnsafe();
        testSafe();
    }
}

