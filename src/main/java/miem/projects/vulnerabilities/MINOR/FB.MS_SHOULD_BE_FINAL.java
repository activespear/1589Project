package miem.projects.vulnerabilities.MINOR.FB;

public class FB_MS_SHOULD_BE_FINAL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Уязвимый класс с изменяемым статическим полем
    public static class ConfigIncorrect {
        public static String API_KEY = "initial_key"; // Поле может быть изменено
    }

    // Исправленный класс с final-полем
    public static class ConfigCorrect {
        public static final String API_KEY = "secure_key"; // Неизменяемое поле
    }

    public static void incorrectTest() {
        // Вредоносный код или ошибка может перезаписать значение
        ConfigIncorrect.API_KEY = "hacked_key";
        System.out.println("Incorrect API Key: " + ConfigIncorrect.API_KEY); // "hacked_key"
    }

    public static void correctTest() {
        // Попытка изменения final-поля приведёт к ошибке компиляции
        // ConfigCorrect.API_KEY = "new_key"; // Ошибка: cannot assign a value to final variable
        System.out.println("Correct API Key: " + ConfigCorrect.API_KEY); // "secure_key"
    }
}