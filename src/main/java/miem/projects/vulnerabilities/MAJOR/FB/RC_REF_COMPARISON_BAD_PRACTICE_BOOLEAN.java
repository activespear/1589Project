package miem.projects.vulnerabilities.MAJOR.FB;

public class RC_REF_COMPARISON_BAD_PRACTICE_BOOLEAN {

    // Небезопасное сравнение через == для Boolean объектов
    static void unsafe() {
        Boolean a = new Boolean(true);
        Boolean b = new Boolean(true);
        if (a == b) {  // сравнение ссылок, а не значений
            System.out.println("Unsafe: Equal");
        } else {
            System.out.println("Unsafe: Not Equal");
        }
    }

    // Безопасное сравнение через equals()
    static void safe() {
        Boolean a = new Boolean(true);
        Boolean b = new Boolean(true);
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

