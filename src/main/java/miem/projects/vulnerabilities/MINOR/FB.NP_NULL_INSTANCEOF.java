package miem.projects.vulnerabilities.MINOR.FB;

public class NP_NULL_INSTANCEOF {
    // Некорректный пример
    public static void incorrectExample(Object obj) {
        if (obj == null) {
            System.out.println("Объект null");
        }
        // Бессмысленная проверка, так как obj может быть null
        if (obj instanceof String) {
            System.out.println("Это строка: " + obj);
        }
    }

    // Корректный пример
    public static void correctExample(Object obj) {
        if (obj == null) {
            System.out.println("Объект null");
        } else if (obj instanceof String) { // Проверка типа только для ненулевых объектов
            System.out.println("Это строка: " + obj);
        }
    }

    public static void main(String[] args) {
        incorrectExample(null);  // Выведет "Объект null", но пропустит бессмысленный instanceof
        correctExample(null);    // Корректно обработает null
        correctExample("test");  // Корректно определит тип
    }
}