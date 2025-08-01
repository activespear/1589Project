package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Arrays;

public class EC_BAD_ARRAY_COMPARE {

    // Небезопасная конструкция — сравнение массивов с помощью equals (сравниваются ссылки)
    public static boolean unsafeEquals(int[] a, int[] b) {
        return a.equals(b);
    }

    // Безопасная конструкция — сравнение содержимого массивов с помощью Arrays.equals
    public static boolean safeEquals(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};

        System.out.println("Unsafe equals: " + unsafeEquals(a, b)); // Обычно false, сравниваются ссылки
        System.out.println("Safe equals: " + safeEquals(a, b));     // true, сравнивается содержимое
    }
}

