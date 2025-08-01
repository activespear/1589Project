package miem.projects.vulnerabilities.MAJOR.FB;

public class NP_CLONE_COULD_RETURN_NULL {

    static class UnsafeClone implements Cloneable {
        private boolean someEdgeCase;

        public UnsafeClone(boolean someEdgeCase) {
            this.someEdgeCase = someEdgeCase;
        }

        // Небезопасный clone, может вернуть null
        @Override
        public Object clone() {
            if (someEdgeCase) {
                return null;
            }
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                // Это не должно случиться, т.к. класс Cloneable
                throw new AssertionError(e);
            }
        }
    }

    static class SafeClone implements Cloneable {
        // Безопасный clone с правильной обработкой исключений
        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }
    }

    static void unsafeUsage() {
        UnsafeClone obj1 = new UnsafeClone(true);
        Object clone1 = obj1.clone();
        System.out.println("Unsafe clone with edge case returns: " + clone1);

        UnsafeClone obj2 = new UnsafeClone(false);
        Object clone2 = obj2.clone();
        System.out.println("Unsafe clone normal returns: " + clone2);
    }

    static void safeUsage() {
        SafeClone obj = new SafeClone();
        Object clone = obj.clone();
        System.out.println("Safe clone returns: " + clone);
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeUsage:");
        unsafeUsage();

        System.out.println("\nRunning safeUsage:");
        safeUsage();
    }
}

