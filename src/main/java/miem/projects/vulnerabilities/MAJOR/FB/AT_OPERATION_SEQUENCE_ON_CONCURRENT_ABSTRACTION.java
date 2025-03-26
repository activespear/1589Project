package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.concurrent.ConcurrentHashMap;

public class AT_OPERATION_SEQUENCE_ON_CONCURRENT_ABSTRACTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        String key = "example";
        if (map.contains(key)) {
            // другой поток может изменить
            // карту после этой проверки
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    public static void correctTest() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        String key = "example";
        // Гарантированно атомарная операция
        map.compute(key, (k, v) -> (v == null) ? 1 : v + 1);
    }
}
