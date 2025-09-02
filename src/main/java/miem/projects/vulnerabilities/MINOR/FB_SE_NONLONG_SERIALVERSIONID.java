package miem.projects.vulnerabilities.MINOR.FB;

import java.io.Serializable;

public class FB_SE_NONLONG_SERIALVERSIONID {

    // Потенциально небезопасное определение SerialVersionUID
    static class ExampleUnsafe implements Serializable {
        private static final int SerialVersionUID = 42;
    }

    // Корректная конструкция
    static class ExampleSafe implements Serializable {
        private static final long SerialVersionUID = 42L;
    }

    public static void main(String[] args) {
        ExampleSafe safe = new ExampleSafe();
        System.out.println("Safe Serializable object created with correct SerialVersionUID");
    }
}
