package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DM_INVALID_MIN_MAX {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: неправильный порядок min/max
        int a = 5, b = 10;
        int min = Math.max(a, b);  // Ошибка: должно быть min
        int max = Math.min(a, b);  // Ошибка: должно быть max
        
        System.out.println("Incorrect min: " + min + ", max: " + max);
    }

    public static void correctTest() {
        // Корректно: правильное использование min/max
        int a = 5, b = 10;
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        
        System.out.println("Correct min: " + min + ", max: " + max);
        
        // Альтернатива с явным сравнением
        int altMin = a < b ? a : b;
        int altMax = a > b ? a : b;
        System.out.println("Alternative min: " + altMin + ", max: " + altMax);
    }
}