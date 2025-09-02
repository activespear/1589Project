package miem.projects.vulnerabilities.MINOR;

public class UNUSED_VALUE_PARAM_ASSIGN_NULL {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        processor.incorrectProcess("test");
        processor.correctProcess("test");
    }

    // Потенциально небезопасное
    static class DataProcessor {
        public void incorrectProcess(String data) {
            // ...
            data = null;  // Подозрительное обнуление параметра
            // Значение data больше не используется
            System.out.println("Processing complete");
        }

        // Корректная конструкция
        public void correctProcess(String data) {
            // ...
            // Убрали бесполезное обнуление
            System.out.println("Processing complete");
        }
    }
}
