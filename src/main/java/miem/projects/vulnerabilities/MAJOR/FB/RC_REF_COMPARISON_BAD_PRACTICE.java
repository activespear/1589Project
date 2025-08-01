package miem.projects.vulnerabilities.MAJOR.FB;

public class RC_REF_COMPARISON_BAD_PRACTICE {

    // Небезопасное сравнение через == для Integer объектов
    static void unsafe() {
        Integer a = new Integer(100);
        Integer b = new Integer(100);
        if (a == b) {  // сравнение ссылок, а не значений
            System.out.println("Unsafe: Equal");
        } else {
            System.out.println("Unsafe: Not Equal");
        }
    }

    // Безопасное сравнение через equals()
    static void safe() {
        Integer a = new Integer(100);
        Integer b = new Integer(100);
        if (a.equals(b)) {  // сравниваем значения
            System.out.println("Safe: Equal");
        } else {
            System.out.println("Safe: Not Equal");
        }
    }

    public static void main(String[] args) {
        System.out.println("Running unsafe comparison:");
        unsafe();

        System.out.println("\nRunning safe comparison:");
        safe();
    }
}

