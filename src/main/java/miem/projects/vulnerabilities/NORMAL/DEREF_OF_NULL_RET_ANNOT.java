package miem.projects.vulnerabilities.NORMAL;

import jakarta.annotation.Nullable;

public class DEREF_OF_NULL_RET_ANNOT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String result = getNullableString();
        // результат не проверяется на null перед разыменованием
        System.out.println(result.length());
    }

    public static void correctTest() {
        String result = getNullableString();
        // Проверка на null перед разыменованием
        if (result != null) {
            System.out.println(result.length());
        } else {
            System.out.println("Результат равен null");
        }
    }

    @Nullable
    public static String getNullableString() {
        return Math.random() > 0.5 ? "Hello" : null;
    }
}