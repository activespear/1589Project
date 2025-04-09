package miem.projects.vulnerabilities.MINOR.FB;

public class MS_MUTABLE_ARRAY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Уязвимый пример
    public static void incorrectTest() {
        Configuration.configParams[0] = "modified_value"; // Прямая модификация массива
        System.out.println("Некорректное значение: " + Configuration.configParams[0]);
    }

    // Исправленный пример
    public static void correctTest() {
        ImmutableConfig.updateParameter(0, "secure_value");
        System.out.println("Защищенное значение: " + ImmutableConfig.getParameter(0));
    }
}

// Уязвимый класс
class Configuration {
    public static final String[] configParams = {"default1", "default2"}; // Уязвимость: публичный неизменяемый массив
}

// Защищенный класс
class ImmutableConfig {
    private static final String[] params = {"default1", "default2"};

    // Возвращаем копию массива
    public static String[] getParameters() {
        return params.clone();
    }

    // Получение элемента с проверкой индекса
    public static String getParameter(int index) {
        if(index >= 0 && index < params.length) {
            return params[index];
        }
        throw new IllegalArgumentException("Invalid index");
    }

    // Безопасное обновление элемента
    public static void updateParameter(int index, String value) {
        if(index >= 0 && index < params.length) {
            params[index] = value;
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }
}
