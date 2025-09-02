package miem.projects.vulnerabilities.MINOR.FB;

public class FB_ISC_INSTANTIATE_STATIC_CLASS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Потенциально небезопасно: создаем объект класса с только статическими методами
        Utils utils = new Utils();
        utils.printMessage("Hello from incorrectTest!");
    }

    public static void correctTest() {
        // Корректно: прямой вызов статического метода без создания объекта
        Utils.printMessage("Hello from correctTest!");
    }

    // Класс с только статическими методами
    static class Utils {
        public static void printMessage(String message) {
            System.out.println(message);
        }
    }
}
