package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RV_CHECK_FOR_POSITIVE_INDEXOF {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String text = "Hello world";
        String search = "world";
        
        // Некорректно: избыточная проверка на > 0
        int index = text.indexOf(search);
        if (index > 0) {  // Должно быть >= 0
            System.out.println("Found at position: " + index);
        } else {
            System.out.println("Not found");
        }
    }

    public static void correctTest() {
        String text = "Hello world";
        String search = "world";
        
        // Корректно: правильная проверка результата indexOf
        int index = text.indexOf(search);
        if (index >= 0) {  // Правильная проверка
            System.out.println("Found at position: " + index);
        } else {
            System.out.println("Not found");
        }
    }
}