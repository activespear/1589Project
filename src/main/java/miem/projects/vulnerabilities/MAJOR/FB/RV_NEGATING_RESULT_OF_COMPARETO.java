package miem.projects.vulnerabilities.MAJOR.FB;

public class RV_NEGATING_RESULT_OF_COMPARETO {

    // Небезопасное использование - отрицание результата compareTo
    static void unsafeCompare(String a, String b) {
        int result = a.compareTo(b);
        if (-result > 0) {  // неверная логика
            System.out.println("Unsafe: a > b");
        } else {
            System.out.println("Unsafe: a <= b");
        }
    }

    // Безопасное использование - прямое сравнение результата compareTo
    static void safeCompare(String a, String b) {
        int result = a.compareTo(b);
        if (result < 0) {
            System.out.println("Safe: a < b");
        } else if (result > 0) {
            System.out.println("Safe: a > b");
        } else {
            System.out.println("Safe: a == b");
        }
    }

    public static void main(String[] args) {
        String a = "apple";
        String b = "banana";

        System.out.println("Running unsafe compare:");
        unsafeCompare(a, b);

        System.out.println("\nRunning safe compare:");
        safeCompare(a, b);
    }
}

