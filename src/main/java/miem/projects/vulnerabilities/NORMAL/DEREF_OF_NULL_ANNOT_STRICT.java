package miem.projects.vulnerabilities.NORMAL;

import jakarta.annotation.Nullable;

public class DEREF_OF_NULL_ANNOT_STRICT {
    public static void main(String[] args) {
        incorrectTest(Math.random() > 0.5 ? null : "text");
        correctTest(Math.random() > 0.5 ? null : "text");
    }

    public static void incorrectTest(@Nullable String input) {
        if (input != null) {
            // Строгий анализатор может всё равно посчитать это потенциально опасным разыменованием
            System.out.println(input.length());
        }
    }

    public static void correctTest(@Nullable String input) {
        if (input == null) {
            System.out.println("Строка равна null");
            return;
        }
        // разыменование вне блока условия
        System.out.println(input.length());
    }
}