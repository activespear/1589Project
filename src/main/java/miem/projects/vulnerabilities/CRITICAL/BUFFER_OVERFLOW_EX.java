package miem.projects.vulnerabilities.CRITICAL;

public class BUFFER_OVERFLOW_EX {

    public enum Type {
        TYPE_ONE,
        TYPE_TWO,
        TYPE_THREE,
        TYPE_INVALID
    }

    // Unsafe implementation
    public static class UnsafeExample {
        public static Type getType(int data) {
            if (data == 100) return Type.TYPE_ONE;
            if (data == 200) return Type.TYPE_TWO;
            return Type.TYPE_INVALID;
        }

        public static String getName(String[] names, Type type) {
            return names[type.ordinal()];  // Potential array index overflow
        }

        public static String example(int data, int flag) {
            String[] names = {"First", "Second", "Third"};
            Type type = (flag != 0) ? Type.TYPE_THREE : getType(data);
            return getName(names, type);
        }
    }

    // Safe implementation
    public static class SafeExample {
        public static Type getType(int data) {
            if (data == 100) return Type.TYPE_ONE;
            if (data == 200) return Type.TYPE_TWO;
            return Type.TYPE_INVALID;
        }

        public static String getName(String[] names, Type type) {
            if (type == Type.TYPE_INVALID || type.ordinal() >= names.length) {
                return "Invalid type";
            }
            return names[type.ordinal()];  // Safe access with bounds checking
        }

        public static String example(int data, int flag) {
            String[] names = {"First", "Second", "Third"};
            Type type = (flag != 0) ? Type.TYPE_THREE : getType(data);
            return getName(names, type);
        }
    }

    public static void main(String[] args) {
        System.out.println("BUFFER_OVERFLOW.EX DEMONSTRATION\n");

        // Test cases
        int[] testData = {100, 200, 300};
        int[] testFlags = {0, 1};

        System.out.println("=== SAFE EXAMPLE ===");
        for (int data : testData) {
            for (int flag : testFlags) {
                try {
                    String result = SafeExample.example(data, flag);
                    System.out.printf("data=%d, flag=%d => %s\n", data, flag, result);
                } catch (Exception e) {
                    System.out.printf("data=%d, flag=%d => ERROR: %s\n",
                            data, flag, e.getClass().getSimpleName());
                }
            }
        }

        System.out.println("\n=== UNSAFE EXAMPLE ===");
        for (int data : testData) {
            for (int flag : testFlags) {
                try {
                    String result = UnsafeExample.example(data, flag);
                    System.out.printf("data=%d, flag=%d => %s\n", data, flag, result);
                } catch (Exception e) {
                    System.out.printf("data=%d, flag=%d => ERROR: %s\n",
                            data, flag, e.getClass().getSimpleName());
                }
            }
        }

        // Demonstrate the overflow case explicitly
        System.out.println("\n=== OVERFLOW DEMONSTRATION ===");
        try {
            String[] names = {"First", "Second"};
            String result = UnsafeExample.getName(names, Type.TYPE_THREE);
            System.out.println("Unsafe access result: " + result);
        } catch (Exception e) {
            System.out.println("Unsafe access failed: " + e.getClass().getSimpleName());
        }

        try {
            String[] names = {"First", "Second"};
            String result = SafeExample.getName(names, Type.TYPE_THREE);
            System.out.println("Safe access result: " + result);
        } catch (Exception e) {
            System.out.println("Safe access failed: " + e.getClass().getSimpleName());
        }
    }
}
