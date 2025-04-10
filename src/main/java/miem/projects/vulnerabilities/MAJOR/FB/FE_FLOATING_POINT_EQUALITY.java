package miem.projects.vulnerabilities.MAJOR.FB;

public class FE_FLOATING_POINT_EQUALITY {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        double a = 0.1 * 3;
        double b = 0.3;

        // сравнение значений с плавающей запятой на точное равенство
        if (a == b) {
            System.out.println("Значения равны");
        } else {
            System.out.println("Значения не равны");
        }
    }

    public static void correctTest() {
        double a = 0.1 * 3;
        double b = 0.3;
        final double EPSILON = 1e-9;

        // проверяем разницу значений в пределах допустимой погрешности
        if (Math.abs(a - b) < EPSILON) {
            System.out.println("Значения равны");
        } else {
            System.out.println("Значения не равны");
        }
    }
}