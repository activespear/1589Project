package miem.projects.vulnerabilities.NORMAL;

public class INT_OVERFLOW_LOOP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int result = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += i;
            if (result < 0) {
                System.out.println("Overflow detected!");
                break;
            }
        }
    }

    public static void correctTest() {
        long result = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            // Проверка на переполнение до выполнения операции
            if (result + i > Integer.MAX_VALUE) {
                System.out.println("Overflow detected!");
                break;
            }
            result += i;
        }
    }
}