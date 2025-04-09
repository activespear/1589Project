package miem.projects.vulnerabilities.MINOR.FB;

public class FB_UC_USELESS_CONDITION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: бесполезное условие
        boolean flag = true;
        
        if (flag == true) {  // Избыточная проверка
            System.out.println("Flag is true");
        }
        
        // Другой пример
        String text = "constant";
        if (text != null && "constant".equals(text)) {  // Первая проверка избыточна
            System.out.println("Text matches");
        }
    }

    public static void correctTest() {
        // Корректно: упрощенные условия
        boolean flag = true;
        
        if (flag) {  // Простая проверка
            System.out.println("Flag is true");
        }
        
        // Более эффективная проверка строки
        String text = "constant";
        if ("constant".equals(text)) {  // Безопасно и эффективно
            System.out.println("Text matches");
        }
        
        // Пример с необходимыми проверками
        String input = null;
        if (input != null && input.length() > 0) {  // Здесь проверка оправдана
            System.out.println("Valid input");
        }
    }
}