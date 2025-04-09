package miem.projects.vulnerabilities.MINOR.FB;

public class FB_UC_USELESS_CONDITION_TYPE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: бесполезная проверка типа
        String text = "example";
        
        if (text instanceof String) {  // Всегда true
            System.out.println("Redundant check: " + text.length());
        }
        
        // Другой пример
        Integer number = 10;
        if (number != null && number instanceof Integer) {  // Вторая проверка избыточна
            System.out.println("Double check: " + number);
        }
    }

    public static void correctTest() {
        // Корректно: только необходимые проверки
        String text = "example";
        System.out.println("Direct use: " + text.length());
        
        // Проверка нужна только когда тип неочевиден
        Object obj = "test";
        if (obj instanceof String) {  // Оправданная проверка
            String str = (String) obj;
            System.out.println("Valid length: " + str.length());
        }
        
        // Для nullable объектов
        Integer number = null;
        if (number != null) {  // Достаточно одной проверки
            System.out.println("Number: " + number);
        }
    }
}