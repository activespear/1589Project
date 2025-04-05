package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NP_NULL_PARAM_DEREF_ALL_TARGETS_DANGEROUS {
    public static void main(String[] args) {
        // Пример некорректного использования
        incorrectUsage();

        // Пример корректного использования
        correctUsage();
    }

    public static void incorrectUsage() {
        String potentiallyNullValue = getPotentiallyNullValue();

        // Метод требует ненулевого значения, но передается возможный null
        dangerousMethod(potentiallyNullValue);
    }

    public static void correctUsage() {
        String potentiallyNullValue = getPotentiallyNullValue();

        // Проверка на null перед вызовом метода
        if (potentiallyNullValue != null) {
            dangerousMethod(potentiallyNullValue);
        } else {
            System.out.println("Ошибка: параметр равен null.");
        }
    }

    public static String getPotentiallyNullValue() {
        // Возвращает значение, которое может быть null
        return Math.random() > 0.5 ? "ValidString" : null;
    }

    public static void dangerousMethod(String nonNullParam) {
        // Метод требует ненулевого параметра
        System.out.println("Обработано значение: " + nonNullParam);
    }
}
