package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_LOCAL_SELF_ASSIGNMENT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int value = 10;
        value = value;

        System.out.println("Некорректное значение: " + value);
    }

    public static void correctTest() {
        int value = 10;
        // обновление значения
        value += 5;

        System.out.println("Корректное значение: " + value);
    }
}