package miem.projects.vulnerabilities.MINOR.FB;

import javax.annotation.Nonnull;

public class NP_NONNULL_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR {
    // Некорректный пример
    public static class IncorrectExample {
        @Nonnull private String data; // Не инициализировано в конструкторе

        public IncorrectExample() {
            // Поле data остается null
        }
    }

    // Корректный пример
    public static class CorrectExample {
        @Nonnull private String data = ""; // Инициализация по умолчанию

        public CorrectExample() {
            // Дополнительная логика, если требуется
        }

        // Или инициализация в конструкторе:
        public CorrectExample(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        // Некорректное использование вызовет ошибку:
        // IncorrectExample obj = new IncorrectExample();
        // System.out.println(obj.data.length()); // NullPointerException

        // Корректное использование:
        CorrectExample safeObj = new CorrectExample("test");
        System.out.println(safeObj.data.length()); // Без ошибок
    }
}