package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EI_EXPOSE_STATIC_REP2 {

    // Проблемные примеры
    public static final List<String> UNSAFE_KEYS = new ArrayList<>(); // 1. Публичная изменяемая коллекция
    public static String[] ADMIN_NAMES = {"root", "admin"};           // 2. Публичный изменяемый массив

    // Корректные аналоги
    private static final List<String> SAFE_KEYS = new ArrayList<>();
    private static final String[] INTERNAL_NAMES = {"root", "admin"};

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Модификация извне
        UNSAFE_KEYS.add("hacked_key"); 
        ADMIN_NAMES[0] = "attacker";
    }

    public static void correctTest() {
        // Безопасный доступ через методы
        addKey("secure_key");
        getAdminNames()[0] = "try_hack"; // Не изменит реальный массив
    }

    // Методы для безопасного доступа
    public static void addKey(String key) {
        SAFE_KEYS.add(key);
    }

    public static List<String> getKeys() {
        return Collections.unmodifiableList(SAFE_KEYS);
    }

    public static String[] getAdminNames() {
        return INTERNAL_NAMES.clone(); // Защитное копирование
    }
}