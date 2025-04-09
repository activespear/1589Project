package miem.projects.vulnerabilities.MINOR.FB;

public class HARDCODED_PASSWORD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректное хранение пароля в коде
    public static void incorrectTest() {
        String username = "admin";
        String password = "P@ssw0rd123!"; // ОШИБКА: пароль в открытом виде
        authenticate(username, password);
    }

    // Безопасное хранение учетных данных
    public static void correctTest() {
        String username = "admin";
        String password = System.getenv("DB_PASSWORD"); // Безопасно: из переменных окружения
        if (password != null) {
            authenticate(username, password);
        } else {
            System.err.println("Пароль не установлен в переменных окружения");
        }
    }

    private static void authenticate(String user, String pass) {
        System.out.println("Аутентификация пользователя: " + user);
    }
}