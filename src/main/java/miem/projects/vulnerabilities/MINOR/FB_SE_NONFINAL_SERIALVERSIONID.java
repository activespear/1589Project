package miem.projects.vulnerabilities.MINOR.FB;

import java.io.Serializable;

public class FB_SE_NONFINAL_SERIALVERSIONID {

    // Потенциально небезопасное определение SerialVersionUID
    static class ExampleUnsafe implements Serializable {
        public static long SerialVersionUID = 123456789L;
        private String name;

        public ExampleUnsafe(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // Корректная конструкция
    static class ExampleSafe implements Serializable {
        private static final long SerialVersionUID = 123456789L; // ✅ final

        private String name;

        public ExampleSafe(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe("Test Name");
        System.out.println("Safe Serializable object name: " + safe.getName());
    }
}
