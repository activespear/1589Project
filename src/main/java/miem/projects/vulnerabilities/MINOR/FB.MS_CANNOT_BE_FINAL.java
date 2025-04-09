package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MS_CANNOT_BE_FINAL {

    // Проблемные примеры
    public static List<String> UNSAFE_LIST = new ArrayList<>(); // 1. Не-final + изменяемый
    
    private static String[] INTERNAL_DATA = {"A", "B", "C"};    // 2. Не-final массив

    // Корректные аналоги
    private static final List<String> SAFE_LIST = new ArrayList<>();
    private static final String[] READONLY_DATA = {"X", "Y", "Z"};

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Изменение ссылки на статическое поле
        UNSAFE_LIST = new ArrayList<>(); // Опасная операция!
        
        // Модификация содержимого
        INTERNAL_DATA[0] = "Hacked"; 
    }

    public static void correctTest() {
        // Безопасное использование через методы
        addToSafeList("Value");
        String[] copy = getReadonlyData(); // Защитная копия
        copy[0] = "Try"; // Не влияет на оригинал
    }

    // Методы для безопасного доступа
    public static void addToSafeList(String item) {
        SAFE_LIST.add(item);
    }

    public static List<String> getSafeList() {
        return Collections.unmodifiableList(SAFE_LIST);
    }

    public static String[] getReadonlyData() {
        return READONLY_DATA.clone();
    }
}