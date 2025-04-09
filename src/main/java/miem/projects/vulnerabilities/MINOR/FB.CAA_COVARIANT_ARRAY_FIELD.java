package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CAA_COVARIANT_ARRAY_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректно: ковариантное поле массива
    private static Object[] fieldArray = new String[5];

    public static void incorrectTest() {
        try {
            fieldArray[0] = 123;  // Попытка сохранить Integer в String[]
            System.out.println("Element stored: " + fieldArray[0]);
        } catch (ArrayStoreException e) {
            System.out.println("Caught ArrayStoreException as expected");
        }
    }

    // Корректно: типобезопасное поле массива
    private static String[] safeFieldArray = new String[5];

    public static void correctTest() {
        safeFieldArray[0] = "safe text";  // Безопасное хранение
        System.out.println("Element stored safely: " + safeFieldArray[0]);
        
        // Альтернатива с проверкой типа
        Object element = "another safe text";
        if (element instanceof String) {
            fieldArray[0] = element;
            System.out.println("Field array element stored with type check: " + fieldArray[0]);
        }
    }
}