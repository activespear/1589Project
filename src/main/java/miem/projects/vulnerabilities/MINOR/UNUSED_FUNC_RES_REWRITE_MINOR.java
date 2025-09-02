package miem.projects.vulnerabilities.MINOR;

import java.util.ArrayList;
import java.util.List;

public class UNUSED_FUNC_RES_REWRITE_MINOR {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String item = "Hello";

        incorrectTest(list, item);
        correctTest(list, item);
    }

    // Потенциально небезопасное: результат вызова игнорируется
    public static void incorrectTest(List<String> list, String item) {
        list.add(item);  // Результат не проверяется
        System.out.println("List after add (incorrect): " + list);
    }

    // Корректная конструкция: результат проверяется
    public static void correctTest(List<String> list, String item) {
        if (!list.add(item)) {
            System.out.println("Item was not added to the list");
        } else {
            System.out.println("Item added successfully: " + list);
        }
    }
}
