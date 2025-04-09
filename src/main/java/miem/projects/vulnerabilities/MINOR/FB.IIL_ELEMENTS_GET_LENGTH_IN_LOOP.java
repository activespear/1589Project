package miem.projects.vulnerabilities.MINOR.FB;

public class FB_IIL_ELEMENTS_GET_LENGTH_IN_LOOP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String[] items = {"A", "B", "C", "D", "E"};
        
        // Некорректно: получение длины в каждой итерации
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i] + " - " + items.length);  // items.length вызывается многократно
        }
    }

    public static void correctTest() {
        String[] items = {"A", "B", "C", "D", "E"};
        
        // Корректно: кэширование длины перед циклом
        int length = items.length;
        for (int i = 0; i < length; i++) {
            System.out.println(items[i] + " - " + length);
        }
        
        // Альтернатива с for-each
        for (String item : items) {
            System.out.println(item);
        }
    }
}