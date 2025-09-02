package miem.projects.vulnerabilities.NORMAL.FB;

import static org.junit.Assert.assertTrue;

public class JUA_DONT_ASSERT_INSTANCEOF_IN_TESTS {

    public static void main(String[] args) {
        JUA_DONT_ASSERT_INSTANCEOF_IN_TESTS test = new JUA_DONT_ASSERT_INSTANCEOF_IN_TESTS();
        test.badPractice();
        test.goodPractice();
    }

    // ❌ Потенциально небезопасное:
    // использование assertTrue(obj instanceof ...) в тестах
    // дает неинформативное сообщение об ошибке ("false is not true")
    public void badPractice() {
        Object obj = getObject();
        assertTrue(obj instanceof String); // плохая практика
        String str = (String) obj;
        System.out.println("Строка: " + str);
    }

    // ✅ Корректная конструкция:
    // при ошибке сразу получим ClassCastException с указанием фактического типа
    public void goodPractice() {
        Object obj = getObject();
        String str = (String) obj; // при ошибке будет понятное исключение
        System.out.println("Строка: " + str);
    }

    private Object getObject() {
        // Для демонстрации иногда возвращаем строку, иногда число
        return Math.random() > 0.5 ? "Hello" : 42;
    }
}