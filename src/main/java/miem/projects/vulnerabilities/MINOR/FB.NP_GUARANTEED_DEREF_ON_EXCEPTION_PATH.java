package miem.projects.vulnerabilities.MINOR.FB;

public class NP_GUARANTEED_DEREF_ON_EXCEPTION_PATH {
    public static void main(String[] args) {
        incorrectExample();
        correctExample();
    }

    // Некорректный код: гарантированное разыменование null на пути исключения
    public static void incorrectExample() {
        String data = null;
        try {
            data = getData(); // Может выбросить исключение
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(data.length()); // NP_GUARANTEED_DEREF_ON_EXCEPTION_PATH сработает здесь
    }

    // Корректный код: проверка на null после блока try-catch
    public static void correctExample() {
        String data = null;
        try {
            data = getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data != null) {
            System.out.println(data.length());
        } else {
            System.out.println("Data is null due to exception!");
        }
    }

    private static String getData() throws Exception {
        if (Math.random() > 0.5) {
            throw new Exception("Random failure");
        }
        return "test";
    }
}