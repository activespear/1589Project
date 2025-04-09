package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RANGE_ARRAY_OFFSET {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: отсутствие проверки offset/length
        byte[] data = {1, 2, 3, 4, 5};
        int offset = 3;
        int length = 10;  // Превышает размер массива
        
        try {
            processData(data, offset, length);  // Опасный вызов
        } catch (ArraыyIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException");
        }
    }

    private static void processData(byte[] data, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            System.out.println(data[i]);  // Опасный доступ
        }
    }

    public static void correctTest() {
        // Корректно: проверка границ перед обработкой
        byte[] data = {1, 2, 3, 4, 5};
        int offset = 3;
        int length = 10;
        
        if (offset >= 0 && length >= 0 && offset + length <= data.length) {
            processDataSafely(data, offset, length);
        } else {
            System.out.println("Invalid offset/length parameters");
        }
        
        // Альтернатива с корректировкой параметров
        int safeOffset = Math.max(0, offset);
        int safeLength = Math.min(length, data.length - safeOffset);
        if (safeLength > 0) {
            processDataSafely(data, safeOffset, safeLength);
        }
    }

    private static void processDataSafely(byte[] data, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            System.out.println(data[i]);
        }
    }
}