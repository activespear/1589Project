package miem.projects.vulnerabilities.MAJOR.FB;

public class NP_BOOLEAN_RETURN_NULL {

    static class UnsafeClass {
        // Небезопасный метод, может вернуть null
        public Boolean isEnabled() {
            if (somethingUnknown()) return null;
            return Boolean.TRUE;
        }

        private boolean somethingUnknown() {
            // Для демонстрации случайное значение
            return Math.random() > 0.5;
        }
    }

    static class SafeClass {
        // Безопасный метод, всегда возвращает boolean
        public boolean isEnabled() {
            return someCheck();
        }

        private boolean someCheck() {
            // Для демонстрации всегда true
            return true;
        }
    }

    // Демонстрация небезопасного вызова (может вызвать NullPointerException)
    static void unsafeUsage() {
        UnsafeClass obj = new UnsafeClass();
        Boolean enabled = obj.isEnabled();
        System.out.println("Unsafe isEnabled() returned: " + enabled);
        if (enabled) {  // Может выбросить NullPointerException, если enabled == null
            System.out.println("Unsafe: doSomething()");
        } else {
            System.out.println("Unsafe: doSomething() skipped");
        }
    }

    // Демонстрация безопасного вызова
    static void safeUsage() {
        SafeClass obj = new SafeClass();
        boolean enabled = obj.isEnabled();
        System.out.println("Safe isEnabled() returned: " + enabled);
        if (enabled) {
            System.out.println("Safe: doSomething()");
        } else {
            System.out.println("Safe: doSomething() skipped");
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeUsage:");
        try {
            unsafeUsage();
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException in unsafeUsage");
        }

        System.out.println("\nRunning safeUsage:");
        safeUsage();
    }
}

