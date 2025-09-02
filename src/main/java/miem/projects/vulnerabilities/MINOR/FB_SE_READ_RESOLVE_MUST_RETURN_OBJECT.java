package miem.projects.vulnerabilities.MINOR.FB;

import java.io.Serializable;

public class FB_SE_READ_RESOLVE_MUST_RETURN_OBJECT {

    // Потенциально небезопасное определение readResolve
    static class ExampleUnsafe implements Serializable {
        private static final ExampleUnsafe INSTANCE = new ExampleUnsafe();

        private ExampleUnsafe() {}

        public ExampleUnsafe readResolve() {
            return INSTANCE; // ❌ Неверный тип возврата, должен быть Object
        }
    }

    // Корректная конструкция
    static class ExampleSafe implements Serializable {
        private static final ExampleSafe INSTANCE = new ExampleSafe();

        private ExampleSafe() {}

        private Object readResolve() {
            return INSTANCE; // ✅ Тип возврата Object
        }
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe();
        System.out.println("Safe Serializable object with correct readResolve");
    }
}
