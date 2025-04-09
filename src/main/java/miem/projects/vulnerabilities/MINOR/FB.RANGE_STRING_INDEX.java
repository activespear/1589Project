package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RANGE_STRING_INDEX {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: отсутствие проверки индекса
        String text = "Hello";
        int index = 10;  // Выход за границы строки
        
        try {
            char ch = text.charAt(index);  // Опасный доступ
            System.out.println("Character: " + ch);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Caught StringIndexOutOfBoundsException");
        }
    }

    public static void correctTest() {
        // Корректно: проверка границ строки
        String text = "Hello";
        int index = 10;
        
        if (index >= 0 && index < text.length()) {
            char ch = text.charAt(index);
            System.out.println("Character: " + ch);
        } else {
            System.out.println("Index out of bounds: " + index);
        }
        
        // Альтернатива с безопасным методом
        try {
            char ch = text.charAt(index);
            System.out.println("Character: " + ch);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Safe handling of index: " + index);
        }
    }
}