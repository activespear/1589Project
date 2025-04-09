package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RCN_REDUNDANT_COMPARISON_OF_NULL_AND_NONNULL_VALUE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String definitelyNotNull = "Hello";
        
        // Некорректно: избыточная проверка на null
        if (definitelyNotNull != null && definitelyNotNull.length() > 0) {
            System.out.println(definitelyNotNull);
        }
    }

    public static void correctTest() {
        String definitelyNotNull = "Hello";
        
        // Корректно: прямая проверка длины
        if (definitelyNotNull.length() > 0) {
            System.out.println(definitelyNotNull);
        }
    }
}