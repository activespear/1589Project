package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Objects;

public class NP_NULL_PARAM_DEREF {
    // Некорректный пример
    public static void unsafeMethod(String data) {
        System.out.println(data.toLowerCase()); // NP_NULL_PARAM_DEREF
    }

    // Корректные примеры
    public static void safeMethod1(String data) {
        if (data != null) {
            System.out.println(data.toLowerCase());
        }
    }

    public static void safeMethod2(String data) {
        Objects.requireNonNull(data, "Data must not be null");
        System.out.println(data.toLowerCase());
    }

    public static void main(String[] args) {
        // unsafeMethod(null); // Выбросит NPE
        safeMethod1(null);     // Безопасно
        safeMethod2("Test");   // Безопасно
    }
}