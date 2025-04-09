package miem.projects.vulnerabilities.MINOR.FB;

public class FB_UM_UNNECESSARY_MATH {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: избыточные математические операции
        double value = Math.abs(-5.0) * Math.signum(-5.0);  // Всегда даст -5.0
        System.out.println("Inefficient value: " + value);
        
        int x = 10;
        int y = Math.max(x, x);  // Бессмысленная операция
        System.out.println("Redundant max: " + y);
    }

    public static void correctTest() {
        // Корректно: упрощенные вычисления
        double value = -5.0;  // Прямое присваивание
        System.out.println("Optimized value: " + value);
        
        int x = 10;
        int y = x;  // Прямое использование значения
        System.out.println("Simplified value: " + y);
    }
}