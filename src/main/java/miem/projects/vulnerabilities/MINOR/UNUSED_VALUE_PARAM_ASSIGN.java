package miem.projects.vulnerabilities.MINOR;

public class UNUSED_VALUE_PARAM_ASSIGN {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        processor.incorrectProcess("  test  ");
        processor.correctProcess("  test  ");
    }

    // Потенциально небезопасное
    static class DataProcessor {
        public void incorrectProcess(String data) {
            data = data.trim();  // Перезаписываем параметр
            // Новое значение data нигде не используется
            System.out.println("Processing complete");
        }

        // Корректная конструкция
        public void correctProcess(String data) {
            // Убрали бесполезную перезапись
            System.out.println("Processing complete");
        }
    }
}
