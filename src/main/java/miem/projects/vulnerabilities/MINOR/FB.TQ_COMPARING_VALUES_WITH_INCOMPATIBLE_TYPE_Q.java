package miem.projects.vulnerabilities.MINOR.FB;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TQ_COMPARING_VALUES_WITH_INCOMPATIBLE_TYPE_QUALIFIERS {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Примеры проблемных сравнений
    public static void incorrectTest() {
        // 1. Сравнение разных примитивных типов
        int intValue = 1000;
        long longValue = 1000L;
        if (intValue == longValue) {  // Неявное приведение int к long
            System.out.println("1. Values are equal");
        }

        // 2. Сравнение объектов разных классов
        String str = "1000";
        Integer num = 1000;
        if (str.equals(num)) {  // Всегда false
            System.out.println("2. Never happens");
        }

        // 3. Использование == для объектов
        String s1 = new String("text");
        String s2 = new String("text");
        if (s1 == s2) {  // Сравнение ссылок, не содержимого
            System.out.println("3. Wrong comparison");
        }

        // 4. Сравнение @Nullable и @Nonnull
        @Nullable String nullable = getNullable();
        @Nonnull String nonnull = "text";
        if (nullable.equals(nonnull)) {  // Возможен NPE
            System.out.println("4. Unsafe comparison");
        }
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Явное приведение типов
        int intValue = 1000;
        long longValue = 1000L;
        if ((long)intValue == longValue) {
            System.out.println("1. Correct type conversion");
        }

        // 2. Сравнение после преобразования
        String str = "1000";
        Integer num = 1000;
        if (str.equals(num.toString())) {
            System.out.println("2. Proper comparison");
        }

        // 3. Использование equals()
        String s1 = new String("text");
        String s2 = new String("text");
        if (s1.equals(s2)) {
            System.out.println("3. Correct object comparison");
        }

        // 4. Безопасное сравнение с null-проверкой
        @Nullable String nullable = getNullable();
        @Nonnull String nonnull = "text";
        if (nonnull.equals(nullable)) {
            System.out.println("4. Safe null-aware comparison");
        }
    }

    @Nullable
    private static String getNullable() {
        return Math.random() > 0.5 ? "text" : null;
    }
}