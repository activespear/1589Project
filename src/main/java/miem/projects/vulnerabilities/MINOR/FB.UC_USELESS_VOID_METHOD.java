package miem.projects.vulnerabilities.MINOR.FB;

public class FB_UC_USELESS_VOID_METHOD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class DataProcessor {
            // Некорректно: бесполезный void-метод
            public void processData(String data) {
                // Ничего не делает
            }
        }
        
        DataProcessor processor = new DataProcessor();
        processor.processData("test");  // Бесполезный вызов
    }

    public static void correctTest() {
        class DataProcessor {
            // Корректно: метод либо удален, либо делает полезную работу
            public String processData(String data) {
                return data != null ? data.toUpperCase() : "";
            }
        }
        
        DataProcessor processor = new DataProcessor();
        String result = processor.processData("test");
        System.out.println("Processed: " + result);
    }
}