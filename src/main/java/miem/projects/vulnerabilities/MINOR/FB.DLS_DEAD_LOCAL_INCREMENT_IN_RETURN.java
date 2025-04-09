package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DLS_DEAD_LOCAL_INCREMENT_IN_RETURN {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: бесполезный инкремент перед return
        int value = getValueIncorrect();
        System.out.println("Value (incorrect): " + value);
    }

    private static int getValueIncorrect() {
        int x = 5;
        return x++;  // Инкремент не имеет эффекта
    }

    public static void correctTest() {
        // Корректно: чистый return без побочных эффектов
        int value = getValueCorrect();
        System.out.println("Value (correct): " + value);
        
        // Или явное увеличение значения если нужно
        int value2 = getAndIncrementValue();
        System.out.println("Incremented value: " + value2);
    }

    private static int getValueCorrect() {
        int x = 5;
        return x;  // Просто возвращаем значение
    }

    private static int getAndIncrementValue() {
        int x = 5;
        return x + 1;  // Явное увеличение с возвратом
    }
}