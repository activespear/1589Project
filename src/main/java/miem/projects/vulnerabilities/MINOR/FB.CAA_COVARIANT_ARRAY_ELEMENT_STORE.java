package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CAA_COVARIANT_ARRAY_ELEMENT_STORE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: ковариантное хранение в массиве
        Object[] objectArray = new String[5];
        try {
            objectArray[0] = 123;  // Попытка сохранить Integer в String[]
            System.out.println("Element stored: " + objectArray[0]);
        } catch (ArrayStoreException e) {
            System.out.println("Caught ArrayStoreException as expected");
        }
    }

    public static void correctTest() {
        // Корректно: использование правильного типа массива
        String[] stringArray = new String[5];
        stringArray[0] = "text";  // Безопасное хранение
        
        // Или проверка типа перед сохранением
        Object[] objectArray = new String[5];
        Object element = "safe text";
        if (element instanceof String) {
            objectArray[0] = element;
            System.out.println("Element stored safely: " + objectArray[0]);
        } else {
            System.out.println("Invalid type for array");
        }
    }
}