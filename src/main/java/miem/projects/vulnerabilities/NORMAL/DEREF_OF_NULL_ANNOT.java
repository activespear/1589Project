package miem.projects.vulnerabilities.NORMAL;

import jakarta.annotation.Nullable;

public class DEREF_OF_NULL_ANNOT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String text = getNullableText();
        // возможен NPE
        System.out.println(text.length());
    }

    public static void correctTest() {
        String text = getNullableText();
        if (text != null) {
            System.out.println(text.length());
        } else {
            System.out.println("Текст отсутствует.");
        }
    }

    @Nullable
    public static String getNullableText() {
        return Math.random() > 0.5 ? "Hello" : null;
    }
}