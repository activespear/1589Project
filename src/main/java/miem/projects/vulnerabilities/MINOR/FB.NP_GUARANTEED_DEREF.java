package miem.projects.vulnerabilities.MINOR.FB;

public class NP_GUARANTEED_DEREF {
    public static void main(String[] args) {
        incorrectExample();
        correctExample();
    }

    // Некорректный код: гарантированное разыменование null
    public static void incorrectExample() {
        String data = null;
        System.out.println(data.length()); // NP_GUARANTEED_DEREF сработает здесь
    }

    // Корректный код: проверка на null
    public static void correctExample() {
        String data = getNullableData();
        if (data != null) {
            System.out.println(data.length());
        } else {
            System.out.println("Data is null!");
        }
    }

    private static String getNullableData() {
        return Math.random() > 0.5 ? "test" : null;
    }
}