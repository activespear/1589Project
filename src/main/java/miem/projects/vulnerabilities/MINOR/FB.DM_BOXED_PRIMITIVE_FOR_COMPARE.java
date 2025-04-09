package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DM_BOXED_PRIMITIVE_FOR_COMPARE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: использование boxed типов для сравнения
        Integer a = 100;
        Integer b = 100;
        
        if (a.compareTo(b) == 0) {  // Избыточное boxed сравнение
            System.out.println("Values are equal (boxed)");
        }
    }

    public static void correctTest() {
        // Корректно: сравнение примитивов
        int a = 100;
        int b = 100;
        
        if (a == b) {  // Простое сравнение примитивов
            System.out.println("Values are equal (primitive)");
        }
        
        // Или для обязательного boxed сравнения - статический метод
        Integer x = 200;
        Integer y = 200;
        if (Integer.compare(x, y) == 0) {  // Без создания промежуточных объектов
            System.out.println("Boxed values compared efficiently");
        }
    }
}