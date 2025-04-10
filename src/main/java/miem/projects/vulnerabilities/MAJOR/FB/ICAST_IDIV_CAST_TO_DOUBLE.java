package miem.projects.vulnerabilities.MAJOR.FB;

public class ICAST_IDIV_CAST_TO_DOUBLE {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int a = 7;
        int b = 3;

        // результат целочисленного деления приводится к double
        double result = (double) (a / b);
        System.out.println("Incorrect Result: " + result);
    }

    public static void correctTest() {
        int a = 7;
        int b = 3;

        // преобразование одного из операндов в double перед делением
        double result = (double) a / b;
        System.out.println("Correct Result: " + result);
    }
}