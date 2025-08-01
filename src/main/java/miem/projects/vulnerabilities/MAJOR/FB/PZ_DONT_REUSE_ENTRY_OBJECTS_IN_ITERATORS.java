package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.*;

public class PZ_DONT_REUSE_ENTRY_OBJECTS_IN_ITERATORS {

    // Небезопасный метод — напрямую берём entrySet и добавляем в список,
    // не создавая копию. Это может привести к проблемам при итерациях и модификациях.
    static void unsafe() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");

        Set<Map.Entry<String, String>> set = map.entrySet(); // view, не копия
        List<Map.Entry<String, String>> list = new ArrayList<>();
        list.addAll(set);

        System.out.println("Unsafe list entries:");
        for (Map.Entry<String, String> entry : list) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    // Безопасный метод — создаём копию entrySet в HashSet, чтобы не зависеть
    // от внутренней структуры map, избегая reuse одного и того же объекта Entry.
    static void safe() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");

        Set<Map.Entry<String, String>> set = new HashSet<>(map.entrySet()); // копия
        List<Map.Entry<String, String>> list = new ArrayList<>();
        list.addAll(set);

        System.out.println("Safe list entries:");
        for (Map.Entry<String, String> entry : list) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe example:");
        unsafe();

        System.out.println("\nRunning safe example:");
        safe();
    }
}

