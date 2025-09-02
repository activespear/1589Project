package miem.projects.vulnerabilities.MINOR;

public class OVERFLOW_AFTER_CHECK_VAR {

    // Некорректное написание
    public static void fooIncorrect() {
        char[] buf = new char[10];
        int size = 10;

        if (size > 0) {
            System.out.println("Valid size");
        }
        buf[size] = '\0'; // потенциальная ошибка
    }

    // Корректное написание
    public static void fooCorrect() {
        char[] buf = new char[10];
        int size = 10;

        if (buf == null || size <= 0) return;
        buf[size - 1] = '\0';
    }

    public static void main(String[] args) {
        fooIncorrect();
        fooCorrect();
    }
}
