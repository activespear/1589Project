package miem.projects.vulnerabilities.NORMAL;

public class UNCHECKED_FUNC_RES_USER_STRICT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Используем результат без проверки
        String data = fetchDataFromServer();
        System.out.println(data.toUpperCase());  // NullPointerException
    }

    public static void correctTest() {
        String data = fetchDataFromServer();
        if (data != null) {
            System.out.println(data.toUpperCase());
        } else {
            System.out.println("Data not available");
        }
    }

    public static String fetchDataFromServer() {
        return null; // Имитация ошибки получения данных
    }
}