package miem.projects.vulnerabilities.CRITICAL;

public class DEREF_AFTER_NULL {

    // Небезопасная операция
    public static class UnsafeOperations {
        public static int unsafeCompare(String str1, String str2, int[] lenHolder) {
            System.out.println("[UNSAFE] Performing unsafe comparison");

            if (str1 == null) {
                lenHolder[0] = 0;
            } else {
                lenHolder[0] = str1.length();
            }

            // Потенциальное NullPointerException
            return str1.compareTo(str2);
        }
    }

    // Безопасная операция
    public static class SafeOperations {
        public static int safeCompare(String str1, String str2, int[] lenHolder) {
            System.out.println("[SAFE] Performing safe comparison");

            if (str1 == null || str2 == null) {
                System.out.println("Null input detected");
                return -1;
            }

            lenHolder[0] = str1.length();
            return str1.compareTo(str2);
        }
    }

    public static void main(String[] args) {
        System.out.println("DEREF_AFTER_NULL DEMONSTRATION\n");

        // Используем массив для хранения длины, так как int передается по значению
        int[] lenHolder = new int[1];

        // Тестовые данные
        String[] testStrings1 = {"hello", null, "world"};
        String[] testStrings2 = {"world", "hello", null};

        // Тестирование безопасной и небезопасной версий
        for (int i = 0; i < testStrings1.length; i++) {
            String s1 = testStrings1[i];
            String s2 = testStrings2[i];

            System.out.println("\n=== Test case " + (i+1) + " ===");
            System.out.println("String1: " + s1);
            System.out.println("String2: " + s2);

            // Безопасная операция
            lenHolder[0] = 0;
            int safeResult = SafeOperations.safeCompare(s1, s2, lenHolder);
            System.out.println("Safe result: " + safeResult + ", length: " + lenHolder[0]);

            // Небезопасная операция
            lenHolder[0] = 0;
            try {
                int unsafeResult = UnsafeOperations.unsafeCompare(s1, s2, lenHolder);
                System.out.println("Unsafe result: " + unsafeResult + ", length: " + lenHolder[0]);
            } catch (NullPointerException e) {
                System.out.println("Unsafe operation threw NullPointerException (expected)");
                System.out.println("Length was: " + lenHolder[0]);
            }
        }

        // Дополнительные тесты
        System.out.println("\n=== Additional tests ===");

        // Оба null
        lenHolder[0] = 0;
        System.out.println("Both null:");
        System.out.println("Safe result: " + SafeOperations.safeCompare(null, null, lenHolder));
        try {
            UnsafeOperations.unsafeCompare(null, null, lenHolder);
        } catch (NullPointerException e) {
            System.out.println("Unsafe threw exception");
        }

        // Первая строка null
        lenHolder[0] = 0;
        System.out.println("\nFirst null:");
        System.out.println("Safe result: " + SafeOperations.safeCompare(null, "test", lenHolder));
        try {
            UnsafeOperations.unsafeCompare(null, "test", lenHolder);
        } catch (NullPointerException e) {
            System.out.println("Unsafe threw exception");
        }

        // Вторая строка null
        lenHolder[0] = 0;
        System.out.println("\nSecond null:");
        System.out.println("Safe result: " + SafeOperations.safeCompare("test", null, lenHolder));
        try {
            UnsafeOperations.unsafeCompare("test", null, lenHolder);
        } catch (NullPointerException e) {
            System.out.println("Unsafe threw exception");
        }
    }
}
