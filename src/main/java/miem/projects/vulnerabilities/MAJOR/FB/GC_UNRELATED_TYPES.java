package miem.projects.vulnerabilities.MAJOR.FB;

import java.util.*;

public class GC_UNRELATED_TYPES {

    // Небезопасная конструкция: попытка получить элемент как неверный тип
    public static void runUnsafe() {
        List<String> list = new ArrayList<>();
        list.add("Hello");

        try {
            Integer num = (Integer) (Object) list.get(0); // Принудительное приведение типа
            System.out.println("Number: " + num);
        } catch (ClassCastException e) {
            System.out.println("Caught exception in unsafe code: " + e);
        }
    }

    // Безопасная конструкция: корректное использование дженериков
    public static void runSafe() {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        String str = list.get(0);  // Корректный тип
        System.out.println("String: " + str);
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe version:");
        runUnsafe();

        System.out.println("\nRunning safe version:");
        runSafe();
    }
}

