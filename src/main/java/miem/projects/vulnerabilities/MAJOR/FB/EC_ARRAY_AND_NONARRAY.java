package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Arrays;

public class EC_ARRAY_AND_NONARRAY {

    // Небезопасная конструкция — вызов equals на массивах (сравнивает ссылки)
    public static boolean unsafeEquals(String[] a, String[] b) {
        return a.equals(b);
    }

    // Безопасная конструкция — использование Arrays.equals для сравнения содержимого массивов
    public static boolean safeEquals(String[] a, String[] b) {
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        String[] a = { "a", "b" };
        String[] b = { "a", "b" };

        System.out.println("Unsafe equals: " + unsafeEquals(a, b)); // Обычно false, т.к. сравниваются ссылки
        System.out.println("Safe equals: " + safeEquals(a, b));     // true, т.к. сравнивается содержимое
    }
}

