package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.List;

public class FB_RV_RETURN_VALUE_IGNORED_INFERRED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        List<String> list = new ArrayList<>();
        // Некорректно: игнорирование возвращаемого значения
        list.add("item");  // Возвращаемое значение игнорируется
        
        String result = processItem("test");
        // Возвращаемое значение не используется
    }

    public static void correctTest() {
        List<String> list = new ArrayList<>();
        // Корректно: проверка возвращаемого значения
        boolean added = list.add("item");
        if (!added) {
            System.out.println("Item not added");
        }
        
        String result = processItem("test");
        System.out.println("Processing result: " + result);
    }

    private static String processItem(String item) {
        return item.toUpperCase();
    }
}