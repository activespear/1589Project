package miem.projects.vulnerabilities.NORMAL;

import jakarta.annotation.Nullable;

public class DEREF_OF_NULL_ANNOT_COND {
    public static void main(String[] args) {
        incorrectTest(Math.random() > 0.5 ? null : "text");
        correctTest(Math.random() > 0.5 ? null : "text");
    }

    public static void incorrectTest(@Nullable String input) {
        if (input != null && input.length() > 0) {
            // Анализатор может посчитать, что условие не гарантирует, что input не null
            System.out.println(input.charAt(0));
        }
    }

    public static void correctTest(@Nullable String input) {
        if (input == null) {
            System.out.println("Строка равна null");
            return;
        }
        // разыменование происходит после явной проверки на null
        System.out.println(input.charAt(0));
    }
}