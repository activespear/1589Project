package miem.projects.vulnerabilities.MINOR.FB;

public class SENSITIVE_LEAK {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректное логирование конфиденциальных данных
    public static void incorrectTest() {
        String password = "s3cr3tP@ss";
        System.out.println("Пользовательский пароль: " + password); // ОШИБКА: утечка
    }

    // Безопасная обработка чувствительных данных
    public static void correctTest() {
        String password = "s3cr3tP@ss";
        System.out.println("Пользователь аутентифицирован"); // Безопасно: без вывода пароля
        // Очистка чувствительных данных из памяти
        password = null;
    }
}