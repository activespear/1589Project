package miem.projects.vulnerabilities.MINOR;

import java.util.ArrayList;
import java.util.List;

public class REDUNDANT_COMPARISON_RET {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        incorrectTest(list);
        correctTest(list);
    }

    // Потенциально небезопасное: избыточное сравнение с true
    public static void incorrectTest(List<String> list) {
        if (list.isEmpty() == true) {
            System.out.println("Список пуст");
        }
    }

    // Корректная конструкция: простая проверка
    public static void correctTest(List<String> list) {
        if (list.isEmpty()) {
            System.out.println("Список пуст");
        }
    }
}
