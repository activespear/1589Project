package miem.projects.vulnerabilities.MINOR.FB;

import javax.annotation.concurrent.Immutable;

public class FB_JCIP_FIELD_ISNT_FINAL_IN_IMMUTABLE_CLASS {

    public static void main(String[] args) {
        System.out.println("Пример демонстрирует некорректное и корректное использование поля в @Immutable классе.");
    }

    // Потенциально небезопасный вариант
    @Immutable
    static class UnsafePerson {
        private String name;  // ❌ Поле не final

        public UnsafePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // Корректный вариант
    @Immutable
    static class SafePerson {
        private final String name;  // ✅ Поле final

        public SafePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
