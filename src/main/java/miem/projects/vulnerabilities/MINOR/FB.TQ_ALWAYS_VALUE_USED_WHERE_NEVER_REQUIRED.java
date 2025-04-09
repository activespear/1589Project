package miem.projects.vulnerabilities.MINOR.FB;

public class TQ_ALWAYS_VALUE_USED_WHERE_NEVER_REQUIRED {

    private static final boolean DEBUG_MODE = false;
    private static final int MAX_RETRIES = 3;

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Примеры избыточных условий
    public static void incorrectTest() {
        // 1. Бессмысленное условие (всегда true)
        if (true) {  // Найдёт FB.TQ_ALWAYS_VALUE_USED_WHERE_NEVER_REQUIRED
            System.out.println("This will always execute");
        }

        // 2. Проверка финализированной константы
        if (DEBUG_MODE) {  // Найдёт, если DEBUG_MODE = false
            System.out.println("Debug info");  // Этот код никогда не выполнится
        }

        // 3. Метод, всегда возвращающий одно значение
        if (alwaysTrue()) {  // Найдёт, если метод всегда возвращает true
            System.out.println("Always true method");
        }
    }

    // Исправленные примеры
    public static void correctTest() {
        // 1. Осмысленное условие
        boolean shouldExecute = checkSomeCondition();
        if (shouldExecute) {
            System.out.println("This executes conditionally");
        }

        // 2. Константа заменена на изменяемое значение
        boolean debugMode = System.getProperty("debug") != null;
        if (debugMode) {
            System.out.println("Debug info");
        }

        // 3. Метод с реальной логикой
        if (checkCondition()) {
            System.out.println("Conditional output");
        }
    }

    // Проблемный метод (всегда возвращает true)
    private static boolean alwaysTrue() {
        return true;  // Найдёт FB.TQ_ALWAYS_VALUE_USED_WHERE_NEVER_REQUIRED
    }

    // Корректный метод с реальной логикой
    private static boolean checkCondition() {
        return System.currentTimeMillis() % 2 == 0;
    }

    private static boolean checkSomeCondition() {
        // Реальная проверка условий
        return Math.random() > 0.5;
    }
}