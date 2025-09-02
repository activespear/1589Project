package miem.projects.vulnerabilities.MINOR.FB;

public class FB_EQ_DONT_DEFINE_EQUALS_FOR_ENUM {

    // ❌ Потенциально небезопасное: переопределение equals в enum
    enum StatusUnsafe {
        ACTIVE, INACTIVE;

        @Override
        public boolean equals(Object obj) {
            return this == obj; // Нарушает контракт equals
        }
    }

    // ✅ Корректная конструкция: стандартное поведение enum
    enum StatusSafe {
        ACTIVE, INACTIVE;
    }

    public static void main(String[] args) {
        // Демонстрация
        System.out.println("Unsafe equals: " + StatusUnsafe.ACTIVE.equals(StatusUnsafe.ACTIVE));
        System.out.println("Safe equals: " + StatusSafe.ACTIVE.equals(StatusSafe.ACTIVE));
    }
}
