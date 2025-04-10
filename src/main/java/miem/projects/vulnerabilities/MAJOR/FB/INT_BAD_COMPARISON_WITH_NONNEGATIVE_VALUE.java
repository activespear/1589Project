package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.ArrayList;
import java.util.List;

public class INT_BAD_COMPARISON_WITH_NONNEGATIVE_VALUE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        List<?> list = new ArrayList<>();
        if (list.size() < 0) {
            // Всегда >=0
        }
    }

    public static void correctTest() {
        List<?> list = new ArrayList<>();
        if (list.isEmpty()) {
            // ...
        }
    }
}