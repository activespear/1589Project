package miem.projects.vulnerabilities.MINOR.FB;

public class TQ_MAYBE_SOURCE_VALUE_REACHES_NEVER_SINK {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Проблемные примеры
    public static void incorrectTest() {
        // 1. Результат метода игнорируется
        String data = fetchData(); // @MaybeSource
        processData(data);        // @NeverSink (ничего не делает с данными)

        // 2. Бессмысленное присваивание
        int computedValue = computeExpensiveValue(); // Тяжелые вычисления
        // computedValue нигде не используется

        // 3. Параметр метода не используется
        logMessage("Important event", true); // Второй параметр игнорируется
    }

    // Корректные аналоги
    public static void correctTest() {
        // 1. Используем возвращаемое значение
        String data = fetchData();
        saveToDatabase(data); // Данные действительно используются

        // 2. Убираем ненужные вычисления
        if (needsComputation()) {
            int value = computeExpensiveValue();
            useValue(value);
        }

        // 3. Упрощаем метод
        logMessage("Important event"); // Убрали неиспользуемый параметр
    }

    // Методы-заглушки
    private static String fetchData() { return "sample data"; }
    private static void processData(String data) {} // Ничего не делает с данными
    private static int computeExpensiveValue() { return 42; }
    private static void logMessage(String msg, boolean flag) { System.out.println(msg); }
    private static void logMessage(String msg) { System.out.println(msg); }
    private static boolean needsComputation() { return true; }
    private static void useValue(int value) {}
    private static void saveToDatabase(String data) {}
}