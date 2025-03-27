package miem.projects.vulnerabilities.MAJOR.FB;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RV_RETURN_VALUE_OF_PUTIFABSENT_IGNORED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Map<String, String> map = new ConcurrentHashMap<>();

        // Игнорируется возвращаемое значение
        map.putIfAbsent("key", "value1");
        // Не перезапишет значение
        map.putIfAbsent("key", "value2");
    }

    public static void correctTest() {
        Map<String, String> map = new ConcurrentHashMap<>();

        String s = map.putIfAbsent("key", "value1");
        if (s == null) {
            // Значение действительно установилось
            s = "value1";
        }
    }
}