package miem.projects.vulnerabilities.MAJOR.FB;

public class ES_COMPARING_PARAMETER_STRING_WITH_EQ {

    // Небезопасная функция: сравнение строки с "yes" через ==
    public static void unsafeCompare(String input) {
        if (input == "yes") {
            System.out.println("Unsafe: Input is yes");
        } else {
            System.out.println("Unsafe: Input is not yes");
        }
    }

    // Безопасная функция: сравнение строки с "yes" через equals
    public static void safeCompare(String input) {
        if ("yes".equals(input)) {
            System.out.println("Safe: Input is yes");
        } else {
            System.out.println("Safe: Input is not yes");
        }
    }

    public static void main(String[] args) {
        String a = new String("yes");
        String b = "yes";

        System.out.println("Testing unsafeCompare with new String(\"yes\"):");
        unsafeCompare(a);  // обычно false из-за сравнения ссылок

        System.out.println("Testing unsafeCompare with literal \"yes\":");
        unsafeCompare(b);  // true, так как сравниваем с той же строкой из пула

        System.out.println("\nTesting safeCompare with new String(\"yes\"):");
        safeCompare(a);    // true

        System.out.println("Testing safeCompare with literal \"yes\":");
        safeCompare(b);    // true
    }
}

