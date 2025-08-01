package miem.projects.vulnerabilities.MAJOR.FB;

public class ES_COMPARING_STRINGS_WITH_EQ {

    // Небезопасное сравнение строк через ==
    public static void unsafeCompare(String a, String b) {
        if (a == b) {
            System.out.println("Unsafe: Strings are the same object");
        } else {
            System.out.println("Unsafe: Strings are different objects");
        }
    }

    // Безопасное сравнение строк через equals
    public static void safeCompare(String a, String b) {
        if (a.equals(b)) {
            System.out.println("Safe: Strings are equal");
        } else {
            System.out.println("Safe: Strings are not equal");
        }
    }

    public static void main(String[] args) {
        String a = new String("hello");
        String b = new String("hello");

        System.out.println("Testing unsafeCompare:");
        unsafeCompare(a, b); // скорее всего выведет, что объекты разные

        System.out.println("Testing safeCompare:");
        safeCompare(a, b);   // выведет, что строки равны
    }
}

