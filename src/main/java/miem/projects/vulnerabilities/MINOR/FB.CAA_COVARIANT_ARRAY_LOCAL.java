package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CAA_COVARIANT_ARRAY_LOCAL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: ковариантная локальная переменная массива
        Object[] localArray = new String[3];
        try {
            localArray[0] = 123;  // Попытка сохранить Integer в String[]
            System.out.println("Local array element: " + localArray[0]);
        } catch (ArrayStoreException e) {
            System.out.println("Caught ArrayStoreException as expected");
        }
    }

    public static void correctTest() {
        // Корректно: использование правильного типа массива
        String[] stringArray = new String[3];
        stringArray[0] = "safe value";
        System.out.println("Local array element: " + stringArray[0]);
        
        // Или безопасное использование с проверкой типа
        Object[] objectArray = new String[3];
        Object element = "checked value";
        if (element instanceof String) {
            objectArray[0] = element;
            System.out.println("Local array stored with check: " + objectArray[0]);
        }
    }
}