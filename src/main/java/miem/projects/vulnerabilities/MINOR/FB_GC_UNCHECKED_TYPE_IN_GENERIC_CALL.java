package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.List;

public class FB_GC_UNCHECKED_TYPE_IN_GENERIC_CALL {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        List<Object> list = new ArrayList<>();

        // Потенциально небезопасно: List<Object> используется с generic методом
        addToList(list, "string"); // Передаем String, хотя список Object
        addToList(list, 123);      // Передаем Integer
        System.out.println("Incorrect list contents: " + list);
    }

    public static void correctTest() {
        List<String> list = new ArrayList<>();

        // Корректная конструкция: типы строго совпадают
        addToList(list, "string");
        addToList(list, "another string");
        System.out.println("Correct list contents: " + list);
    }

    public static <T> void addToList(List<T> list, T item) {
        list.add(item);  // Безопасная вставка с проверкой типа
    }
}
