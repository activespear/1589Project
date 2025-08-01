package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Objects;

public class EQ_CHECK_FOR_OPERAND_NOT_COMPATIBLE_WITH_THIS {

    static class UnsafeClass {
        private String value;

        public UnsafeClass(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            // Небезопасно: проверяет instanceof String, что не имеет смысла для корректного equals
            if (o instanceof String) {
                return false;
            }
            // Заглушка для остальной логики
            return true;
        }
    }

    static class SafeClass {
        private String value;

        public SafeClass(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SafeClass that = (SafeClass) o;
            return Objects.equals(this.value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static void unsafeEqualsTest() {
        UnsafeClass a = new UnsafeClass("test");
        String str = "test";

        System.out.println("Unsafe equals with String operand: " + a.equals(str)); // false (по коду)
        System.out.println("Unsafe equals with UnsafeClass operand: " + a.equals(new UnsafeClass("test"))); // true (заглушка)
    }

    public static void safeEqualsTest() {
        SafeClass a = new SafeClass("test");
        SafeClass b = new SafeClass("test");
        String str = "test";

        System.out.println("Safe equals with SafeClass operand: " + a.equals(b)); // true
        System.out.println("Safe equals with String operand: " + a.equals(str)); // false
    }

    public static void main(String[] args) {
        unsafeEqualsTest();
        safeEqualsTest();
    }
}

