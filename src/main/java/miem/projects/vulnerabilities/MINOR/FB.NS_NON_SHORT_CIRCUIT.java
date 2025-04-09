package miem.projects.vulnerabilities.MINOR.FB;

public class NS_NON_SHORT_CIRCUIT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Некорректное использование не-короткозамкнутого оператора
    public static void incorrectTest() {
        String str = null;
        if (str != null & str.length() > 0) {  // ОШИБКА: & вместо &&
            System.out.println("String is not empty");
        } else {
            System.out.println("This will throw NullPointerException!");
        }
    }

    // Корректное использование короткозамкнутого оператора
    public static void correctTest() {
        String str = null;
        if (str != null && str.length() > 0) {  // Корректно: &&
            System.out.println("String is not empty");
        } else {
            System.out.println("Safe check with short-circuit");
        }
    }
}