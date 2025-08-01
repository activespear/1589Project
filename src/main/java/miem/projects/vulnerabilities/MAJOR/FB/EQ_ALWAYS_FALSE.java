package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Objects;

public class EQ_ALWAYS_FALSE {

    // Небезопасная конструкция: equals всегда возвращает false
    static class UnsafeClass {
        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    // Безопасная конструкция: equals корректно реализован по полю id
    static class SafeClass {
        private int id;

        public SafeClass(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SafeClass that = (SafeClass) obj;
            return id == that.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static void unsafeEqualsTest() {
        UnsafeClass a = new UnsafeClass();
        UnsafeClass b = new UnsafeClass();

        System.out.println("Unsafe equals result: " + a.equals(b));
    }

    public static void safeEqualsTest() {
        SafeClass a = new SafeClass(1);
        SafeClass b = new SafeClass(1);

        System.out.println("Safe equals result: " + a.equals(b));
    }

    public static void main(String[] args) {
        unsafeEqualsTest();
        safeEqualsTest();
    }
}

