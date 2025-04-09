package miem.projects.vulnerabilities.MINOR.FB;

public class SENSITIVE_LEAK_SYSTEM {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректное раскрытие системной информации
    public static void incorrectTest() {
        System.out.println("Системные свойства: " + System.getProperties()); // ОШИБКА: утечка
    }

    // Безопасное логирование системных данных
    public static void correctTest() {
        // Логируем только необходимую минимальную информацию
        System.out.println("ОС: " + System.getProperty("os.name"));
        System.out.println("Версия JVM: " + System.getProperty("java.version"));
    }
}