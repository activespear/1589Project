package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NM_CLASS_NOT_EXCEPTION {

    public static void main(String[] args) {
        System.out.println("Пример демонстрирует некорректное и корректное использование имени класса, оканчивающегося на 'Exception'.");
    }

    // Потенциально небезопасный вариант
    static class InvalidException {
        // Класс не является исключением, но имя заканчивается на "Exception"
        public void someMethod() {
            System.out.println("Некорректное использование имени класса.");
        }
    }

    // Корректный вариант
    static class ProperException extends Exception {
        // Класс корректно наследуется от Exception
        public ProperException(String message) {
            super(message);
        }
    }
}
