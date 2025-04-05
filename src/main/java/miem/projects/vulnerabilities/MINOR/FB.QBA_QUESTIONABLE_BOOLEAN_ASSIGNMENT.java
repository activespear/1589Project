package miem.projects.vulnerabilities.MINOR.FB;

public class QBA_QUESTIONABLE_BOOLEAN_ASSIGNMENT {
    public static void main(String[] args) {
        incorrectExample();
        correctExample();
    }

    // Некорректный пример
    public static void incorrectExample() {
        boolean isReady = false;
        boolean isFinished = false;
        
        // Подозрительное присваивание в условии
        while (isReady = !isFinished) {  // FB.QBA_QUESTIONABLE_BOOLEAN_ASSIGNMENT
            System.out.println("Processing...");
            isFinished = process();
        }
    }

    // Корректный пример
    public static void correctExample() {
        boolean isReady = false;
        boolean isFinished = false;
        
        // Ясное разделение операций
        while (!isFinished) {
            isReady = !isFinished;
            System.out.println("Processing...");
            isFinished = process();
        }
    }

    private static boolean process() {
        // Какая-то логика обработки
        return true;
    }
}