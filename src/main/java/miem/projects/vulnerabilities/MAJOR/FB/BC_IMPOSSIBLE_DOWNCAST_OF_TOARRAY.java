package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.Arrays;
import java.util.List;

public class BC_IMPOSSIBLE_DOWNCAST_OF_TOARRAY {

    // Небезопасная конструкция: может выбросить ClassCastException
    public static void unsafeToArrayCast() {
        List<String> list = Arrays.asList("a", "b", "c");
        try {
            String[] array = (String[]) list.toArray(); // Ошибка: toArray() возвращает Object[]
            System.out.println("Unsafe array: " + Arrays.toString(array));
        } catch (ClassCastException e) {
            System.out.println("Unsafe toArray cast failed: " + e);
        }
    }

    // Безопасная конструкция: правильное использование toArray(T[])
    public static void safeToArrayCast() {
        List<String> list = Arrays.asList("a", "b", "c");
        String[] array = list.toArray(new String[0]); // Безопасно
        System.out.println("Safe array: " + Arrays.toString(array));
    }

    public static void main(String[] args) {
        System.out.println("Running unsafeToArrayCast:");
        unsafeToArrayCast();

        System.out.println("\nRunning safeToArrayCast:");
        safeToArrayCast();
    }
}

