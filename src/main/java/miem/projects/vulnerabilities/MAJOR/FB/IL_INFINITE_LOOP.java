package miem.projects.vulnerabilities.MAJOR.FB;

public class IL_INFINITE_LOOP {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int i = 0;
        while (i < 10) {
            // ...
            // Без условия завершения или
            // с неверным условием
            i--;
        }
    }

    public static void correctTest() {
        int i = 0;
        while (i < 10) {
            // С условием
            i++;
        }
    }
}
