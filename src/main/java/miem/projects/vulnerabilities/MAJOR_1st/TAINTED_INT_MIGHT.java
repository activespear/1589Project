package miem.projects.vulnerabilities.MAJOR_1st;

public class TAINTED_INT_MIGHT {

    // Небезопасный метод: парсит без проверки, может вызвать NumberFormatException или передать отрицательное значение
    public void unsafeTest(String str, int flag) {
        int count;
        if (flag != 0) {
            count = Integer.parseInt(str);
        } else {
            count = -1;
        }

        try {
            System.out.println("Unsafe sleeping for " + count + " ms");
            Thread.sleep(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Безопасный метод: парсит с обработкой исключений и проверкой на отрицательное значение
    public void safeTest(String str, int flag) {
        int count;
        if (flag != 0) {
            try {
                count = Integer.parseInt(str);
                if (count < 0) {
                    count = 0;
                }
            } catch (NumberFormatException e) {
                count = 0;
            }
        } else {
            count = 0;
        }

        try {
            System.out.println("Safe sleeping for " + count + " ms");
            Thread.sleep(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Демонстрация запуска обеих версий
    public static void main(String[] args) {
        TAINTED_INT_MIGHT example = new TAINTED_INT_MIGHT();

        System.out.println("Running unsafeTest:");
        try {
            example.unsafeTest("1000", 1);  // корректный ввод
            example.unsafeTest("-100", 1);  // отрицательное значение - может привести к ошибке
            example.unsafeTest("abc", 1);   // некорректный ввод - NumberFormatException не обработан
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException in unsafeTest");
        }

        System.out.println("\nRunning safeTest:");
        example.safeTest("1000", 1);  // корректный ввод
        example.safeTest("-100", 1);  // отрицательное значение будет исправлено на 0
        example.safeTest("abc", 1);   // некорректный ввод обработан, count=0
        example.safeTest("500", 0);   // flag=0, count=0
    }
}
