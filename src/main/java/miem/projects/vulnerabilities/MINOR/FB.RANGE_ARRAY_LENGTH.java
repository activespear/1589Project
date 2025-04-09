package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RANGE_ARRAY_LENGTH {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: отсутствие проверки размера массива
        int size = -5;  // Может приходить извне
        
        try {
            int[] array = new int[size];  // Опасное создание массива
            System.out.println("Array created with size: " + array.length);
        } catch (NegativeArraySizeException e) {
            System.out.println("Caught NegativeArraySizeException");
        }
    }

    public static void correctTest() {
        // Корректно: валидация размера массива
        int size = -5;
        
        if (size >= 0) {
            int[] array = new int[size];
            System.out.println("Array created with size: " + array.length);
        } else {
            System.out.println("Invalid array size: " + size);
        }
        
        // Альтернатива с ограничением максимального размера
        int maxSize = 1000;
        int safeSize = Math.min(Math.max(size, 0), maxSize);
        int[] safeArray = new int[safeSize];
        System.out.println("Safe array size: " + safeArray.length);
    }
}