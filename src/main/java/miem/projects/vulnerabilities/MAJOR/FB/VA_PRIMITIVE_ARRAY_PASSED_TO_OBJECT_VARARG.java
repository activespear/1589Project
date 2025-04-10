package miem.projects.vulnerabilities.MAJOR.FB;


import java.util.Arrays;

public class VA_PRIMITIVE_ARRAY_PASSED_TO_OBJECT_VARARG {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int[] numbers = {1, 2, 3};
        // программа выведет адрес массив
        printObjects(numbers);
    }

    public static void correctTest() {
        int[] numbers = {1, 2, 3};
        printObjects(Arrays.toString(numbers));
    }

    public static void printObjects(Object... args) {
        for (Object arg : args) {
            System.out.println(arg);
        }
    }
}
