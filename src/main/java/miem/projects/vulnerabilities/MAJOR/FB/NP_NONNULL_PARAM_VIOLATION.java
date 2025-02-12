package miem.projects.vulnerabilities.MAJOR.FB;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public class NP_NONNULL_PARAM_VIOLATION {
    public static void main(String[] args) {
        incorrectTest(null);
        incorrectTest("a");
        correctTest(null);
        correctTest("b");
    }

    public static void incorrectTest(String str) {
        class Example {
            public static void printStr(@Nonnull String str) {
                System.out.println(str);
            }
        }
        // Возможно str = null
        Example.printStr(str);
    }

    public static void correctTest(String str) {
        class Example {
            public static void printStr(@Nonnull String str) {
                System.out.println(str);
            }
        }

        if (Objects.nonNull(str)) {
            Example.printStr(str);
        }
    }
}
