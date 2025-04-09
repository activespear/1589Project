package miem.projects.vulnerabilities.MINOR.FB;

public class FB_RANGE_ARRAY_INDEX {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: отсутствие проверки границ
        int[] data = {1, 2, 3};
        int index = 5;  // Может приходить извне
        
        try {
            System.out.println("Value: " + data[index]);  // Опасный доступ
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException");
        }
    }

    public static void correctTest() {
        // Корректно: проверка границ массива
        int[] data = {1, 2, 3};
        int index = 5;
        
        if (index >= 0 && index < data.length) {
            System.out.println("Value: " + data[index]);
        } else {
            System.out.println("Index out of bounds: " + index);
        }
        
        // Альтернатива с безопасным доступом
        try {
            int value = data[index];
            System.out.println("Value: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Safe handling of index: " + index);
        }
    }
}