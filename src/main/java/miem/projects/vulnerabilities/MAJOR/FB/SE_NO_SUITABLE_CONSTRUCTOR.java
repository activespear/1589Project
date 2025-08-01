package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.Serializable;

public class SE_NO_SUITABLE_CONSTRUCTOR {

    // Небезопасная конструкция: Base без конструктора по умолчанию
    static class BaseUnsafe {
        private int baseField;

        public BaseUnsafe(int baseField) {
            this.baseField = baseField;
        }
    }

    static class DerivedUnsafe extends BaseUnsafe implements Serializable {
        private int derivedField;

        public DerivedUnsafe(int baseField, int derivedField) {
            super(baseField);
            this.derivedField = derivedField;
        }

        @Override
        public String toString() {
            return "DerivedUnsafe{baseField=?, derivedField=" + derivedField + "}";
        }
    }

    // Безопасная конструкция: Base с конструктором по умолчанию
    static class BaseSafe {
        private int baseField;

        public BaseSafe() {
            this.baseField = 0;
        }

        public BaseSafe(int baseField) {
            this.baseField = baseField;
        }
    }

    static class DerivedSafe extends BaseSafe implements Serializable {
        private int derivedField;

        public DerivedSafe(int baseField, int derivedField) {
            super(baseField);
            this.derivedField = derivedField;
        }

        @Override
        public String toString() {
            return "DerivedSafe{derivedField=" + derivedField + "}";
        }
    }

    // Демонстрация работы с небезопасным классом
    public static void unsafeTest() {
        DerivedUnsafe obj = new DerivedUnsafe(10, 20);
        System.out.println("Unsafe object created: " + obj);
        // При сериализации/десериализации может быть ошибка,
        // т.к. BaseUnsafe не имеет конструктора по умолчанию.
    }

    // Демонстрация работы с безопасным классом
    public static void safeTest() {
        DerivedSafe obj = new DerivedSafe(10, 20);
        System.out.println("Safe object created: " + obj);
        // Серийная поддержка корректна благодаря конструктору по умолчанию в BaseSafe.
    }

    public static void main(String[] args) {
        System.out.println("=== Unsafe construction ===");
        unsafeTest();

        System.out.println("\n=== Safe construction ===");
        safeTest();
    }
}

