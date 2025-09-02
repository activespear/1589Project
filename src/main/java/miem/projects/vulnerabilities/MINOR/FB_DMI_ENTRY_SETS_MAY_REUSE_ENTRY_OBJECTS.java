package miem.projects.vulnerabilities.MINOR.FB;

import java.util.*;

public class FB_DMI_ENTRY_SETS_MAY_REUSE_ENTRY_OBJECTS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Map<String, String> map = new IdentityHashMap<>();
        map.put("A", "1");
        map.put("B", "2");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        List<Map.Entry<String, String>> someOtherCollection = new ArrayList<>();
        someOtherCollection.addAll(entries); // Опасно: все записи могут ссылаться на одни объекты Entry

        System.out.println("Entries added insecurely: " + someOtherCollection);
    }

    public static void correctTest() {
        Map<String, String> map = new IdentityHashMap<>();
        map.put("A", "1");
        map.put("B", "2");

        List<Map.Entry<String, String>> someOtherCollection = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            // Создаем отдельный объект Entry для каждой пары
            someOtherCollection.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
        }

        System.out.println("Entries added securely: " + someOtherCollection);
    }
}
