package miem.projects.vulnerabilities.MAJOR.FB;

public class CN_IDIOM {

    // Небезопасная реализация Cloneable — не реализует метод clone()
    static class UnsafeMyData implements Cloneable {
        private int value = 42;

        public int getValue() {
            return value;
        }
    }

    // Безопасная реализация Cloneable — правильно переопределяет метод clone()
    static class SafeMyData implements Cloneable {
        private int value = 99;

        public int getValue() {
            return value;
        }

        @Override
        public SafeMyData clone() {
            try {
                return (SafeMyData) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError("Clone not supported", e);
            }
        }
    }

    public static void runUnsafe() {
        UnsafeMyData data = new UnsafeMyData();
        try {
            // Попытка клонирования без переопределения clone() — вызовет исключение
            UnsafeMyData copy = (UnsafeMyData) data.clone(); // Runtime exception here
            System.out.println("Unsafe clone value: " + copy.getValue());
        } catch (CloneNotSupportedException e) {
            System.out.println("Unsafe clone failed: " + e);
        }
    }

    public static void runSafe() {
        SafeMyData data = new SafeMyData();
        SafeMyData copy = data.clone();
        System.out.println("Safe clone value: " + copy.getValue());
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe clone:");
        runUnsafe();

        System.out.println("\nRunning safe clone:");
        runSafe();
    }
}

