package miem.projects.vulnerabilities.MINOR.FB;

public class FB_CAA_COVARIANT_ARRAY_RETURN {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректно: ковариантный возврат массива
    private static Object[] getStringArray() {
        return new String[5];
    }

    public static void incorrectTest() {
        Object[] array = getStringArray();
        try {
            array[0] = 123;  // Попытка сохранить Integer в String[]
            System.out.println("Element stored: " + array[0]);
        } catch (ArrayStoreException e) {
            System.out.println("Caught ArrayStoreException as expected");
        }
    }

    // Корректно: типобезопасный возврат массива
    private static String[] getSafeStringArray() {
        return new String[5];
    }

    public static void correctTest() {
        String[] array = getSafeStringArray();
        array[0] = "safe value";
        System.out.println("Element stored safely: " + array[0]);
        
        // Альтернатива с проверкой типа
        Object[] objArray = getStringArray();
        Object element = "checked value";
        if (element instanceof String) {
            objArray[0] = element;
            System.out.println("Array element stored with check: " + objArray[0]);
        }
    }
}