package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NP_LOAD_OF_KNOWN_NULL_VALUE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: заведомо null значение
        String name = getName();  // Возвращает null
        System.out.println(name.toUpperCase());  // Гарантированный NPE
    }

    public static void correctTest() {
        // Корректно: защитная проверка
        String name = getName();
        if (name != null) {
            System.out.println(name.toUpperCase());
        } else {
            System.out.println("Name not provided");
        }
    }

    private static String getName() {
        return null;  // Имитация метода, возвращающего null
    }
}