package miem.projects.vulnerabilities.MINOR.FB;

public class FB_TQ_EXPLICIT_UNKNOWN_SOURCE_VALUE_REACHES_NEVER_SINK {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректный случай, который может вызвать ложное срабатывание анализатора
        String data = getAlwaysNonNullData();
        
        if (data == null) {  // Условие никогда не выполнится
            processData(data);  // Sink никогда не будет достигнут
        }
    }

    public static void correctTest() {
        // Корректная реализация без избыточных проверок
        String data = getAlwaysNonNullData();
        processData(data);  // Прямая передача данных
    }

    private static String getAlwaysNonNullData() {
        // Метод, который всегда возвращает non-null значение
        return "constant_data";
    }

    private static void processData(String data) {
        // Метод-приемник (sink)
        System.out.println("Data processed: " + data);
    }
}