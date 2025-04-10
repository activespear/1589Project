package miem.projects.vulnerabilities.MAJOR.FB;

import jakarta.annotation.Nonnull;

public class NP_NONNULL_RETURN_VIOLATION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Example {
            @Nonnull
            public static String getMessage() {
                return null;
            }
        }

        String message = Example.getMessage();
        // NullPointerException:
        System.out.println(message.length());
    }

    public static void correctTest() {
        class Example {
            @Nonnull
            public static String getMessage() {
                return "string";
            }
        }

        String message = Example.getMessage();
        System.out.println(message.length());
    }
}