package miem.projects.vulnerabilities.MINOR.FB;

public class FB_NP_STORE_INTO_NONNULL_FIELD {
    private String nonNullField;

    public static void main(String[] args) {
        FB_NP_STORE_INTO_NONNULL_FIELD example = new FB_NP_STORE_INTO_NONNULL_FIELD();

        // Пример некорректного использования
        example.incorrectUsage();

        // Пример корректного использования
        example.correctUsage();
    }

    public void incorrectUsage() {
        String potentiallyNullValue = getPotentiallyNullValue();

        // Некорректно: сохранение null в поле, которое должно быть ненулевым
        this.nonNullField = potentiallyNullValue;
    }

    public void correctUsage() {
        String potentiallyNullValue = getPotentiallyNullValue();

        // Корректно: проверка на null перед сохранением значения
        if (potentiallyNullValue != null) {
            this.nonNullField = potentiallyNullValue;
        } else {
            System.out.println("Ошибка: параметр равен null.");
        }
    }

    public String getPotentiallyNullValue() {
        // Возвращает значение, которое может быть null
        return Math.random() > 0.5 ? "ValidString" : null;
    }
}
