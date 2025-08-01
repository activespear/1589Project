package miem.projects.vulnerabilities.MAJOR.FB;

public class CN_IMPLEMENTS_CLONE_BUT_NOT_CLONEABLE {

    // Небезопасный класс: реализует clone(), но не Cloneable
    static class UnsafeMyData {
        private int value = 10;

        @Override
        public UnsafeMyData clone() {
            try {
                return (UnsafeMyData) super.clone(); // вызовет CloneNotSupportedException
            } catch (CloneNotSupportedException e) {
                throw new AssertionError("Clone not supported: class does not implement Cloneable");
            }
        }
    }

    // Безопасный класс: правильно реализует Cloneable
    static class SafeMyData implements Cloneable {
        private int value = 20;

        @Override
        public SafeMyData clone() {
            try {
                return (SafeMyData) super.clone(); // безопасно
            } catch (CloneNotSupportedException e) {
                throw new AssertionError("Unexpected CloneNotSupportedException", e);
            }
        }
    }

    public static void runUnsafe() {
        UnsafeMyData data = new UnsafeMyData();
        try {
            UnsafeMyData copy = data.clone(); // приведёт к исключению
            System.out.println("Unsafe clone value: " + copy.value);
        } catch (AssertionError e) {
            System.out.println("Unsafe clone failed: " + e.getMessage());
        }
    }

    public static void runSafe() {
        SafeMyData data = new SafeMyData();
        SafeMyData copy = data.clone();
        System.out.println("Safe clone value: " + copy.value);
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe clone:");
        runUnsafe();

        System.out.println("\nRunning safe clone:");
        runSafe();
    }
}

