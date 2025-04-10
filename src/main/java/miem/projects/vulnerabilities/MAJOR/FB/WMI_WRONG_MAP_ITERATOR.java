package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.HashMap;
import java.util.Map;

public class WMI_WRONG_MAP_ITERATOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class MyClass {
            public void processMap(Map<String, String> map) {
                for (String key : map.keySet()) {
                    // Использование Map.get(key) для получения значения по ключу
                    String value = map.get(key); // Это менее эффективно
                    System.out.println(key + ": " + value);
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("apple", "fruit");
        map.put("carrot", "vegetable");

        MyClass myClass = new MyClass();
        myClass.processMap(map);
    }

    public static void correctTest() {
        class MyClass {
            public void processMap(Map<String, String> map) {
                // Используем итератор по записям Map, чтобы избежать поиска map.get(key)
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    System.out.println(key + ": " + value);
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("apple", "fruit");
        map.put("carrot", "vegetable");

        MyClass myClass = new MyClass();
        myClass.processMap(map);
    }
}
