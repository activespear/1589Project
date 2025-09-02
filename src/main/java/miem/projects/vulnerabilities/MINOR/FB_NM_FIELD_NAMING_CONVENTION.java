package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NM_FIELD_NAMING_CONVENTION {

    public static void main(String[] args) {
        System.out.println("Пример демонстрирует корректное и некорректное именование полей.");
    }

    // Потенциально небезопасный вариант
    static class NamingExampleUnsafe {
        //  Нарушение соглашения: поле не должно начинаться с заглавной буквы
        private String MyField;

        //  Константа не соответствует общепринятому стилю (static final пишется в верхнем регистре)
        private static final String maxValue = "100";
    }

    // Корректный вариант
    static class NamingExampleSafe {
        //  Поле экземпляра в стиле lowerCamelCase
        private String myField;

        //  Константа в верхнем регистре с подчеркиваниями
        private static final String MAX_VALUE = "100";
    }
}
