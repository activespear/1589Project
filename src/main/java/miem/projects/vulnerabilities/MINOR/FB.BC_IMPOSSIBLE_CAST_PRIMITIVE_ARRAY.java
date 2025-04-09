package miem.projects.vulnerabilities.MINOR.FB;

public class FB_BC_IMPOSSIBLE_CAST_PRIMITIVE_ARRAY {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: попытка каста примитивного массива
        int[] intArray = new int[10];
        try {
            Integer[] integerArray = (Integer[]) intArray;  // Невозможный каст
            System.out.println("Array length: " + integerArray.length);
        } catch (ClassCastException e) {
            System.out.println("Caught ClassCastException as expected");
        }
    }

    public static void correctTest() {
        // Корректно: создание нового массива нужного типа
        int[] intArray = new int[10];
        Integer[] integerArray = new Integer[intArray.length];
        
        // Копирование элементов с преобразованием типа
        for (int i = 0; i < intArray.length; i++) {
            integerArray[i] = intArray[i];
        }
        
        System.out.println("Array converted safely. Length: " + integerArray.length);
    }
}