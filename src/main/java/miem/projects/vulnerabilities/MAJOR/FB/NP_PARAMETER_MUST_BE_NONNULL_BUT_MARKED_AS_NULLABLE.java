package miem.projects.vulnerabilities.MAJOR.FB;

import jakarta.annotation.Nullable;

public class NP_PARAMETER_MUST_BE_NONNULL_BUT_MARKED_AS_NULLABLE {
    public static void main(String[] args) {
        incorrectTest(null);
        correctTest(null);
    }

    public static void incorrectTest(@Nullable String data) {
        System.out.println(data.toUpperCase());
    }

    public static void correctTest(@Nullable String data) {
        if (data != null) {
            System.out.println(data.toUpperCase());
        }
    }
}