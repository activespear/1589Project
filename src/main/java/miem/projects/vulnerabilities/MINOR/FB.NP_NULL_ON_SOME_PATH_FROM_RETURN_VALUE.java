package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Map;
import java.util.HashMap;

public class FB_NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    private static Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>();
        if (System.currentTimeMillis() % 2 == 0) { // 50% chance of null
            config.put("key", "value");
        }
        return config;
    }

    public static void incorrectTest() {
        // Некорректно: возможный null на одном из путей выполнения
        String value = getConfig().get("key").toUpperCase();
        System.out.println(value);
    }

    public static void correctTest() {
        // Корректно: защитная проверка всех возможных null
        Map<String, String> config = getConfig();
        if (config != null) {
            String value = config.get("key");
            if (value != null) {
                System.out.println(value.toUpperCase());
            } else {
                System.out.println("Key not found");
            }
        } else {
            System.out.println("Config not initialized");
        }
    }
}