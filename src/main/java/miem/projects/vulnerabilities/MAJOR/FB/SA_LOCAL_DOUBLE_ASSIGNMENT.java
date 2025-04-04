package miem.projects.vulnerabilities.MAJOR.FB;

public class SA_LOCAL_DOUBLE_ASSIGNMENT {
    public static void main(String[] args) {
        incorrectTest(42);
        correctTest(42);
    }

    public static void incorrectTest(int newValue) {
        int value = 5;
        value = newValue;

        System.out.println("Некорректное значение: " + value);
    }

    public static void correctTest(int newValue) {
        int value;
        if (newValue > 0) {  // Присваиваем только при необходимости
            value = newValue;
        } else {
            value = 5;
        }

        System.out.println("Корректное значение: " + value);
    }
}