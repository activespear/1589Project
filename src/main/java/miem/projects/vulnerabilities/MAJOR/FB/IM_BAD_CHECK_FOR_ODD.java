package miem.projects.vulnerabilities.MAJOR.FB;

public class IM_BAD_CHECK_FOR_ODD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int[] numbers = {5, -5, 8, -8};

        for (int num : numbers) {
            // не работает для отрицательных чисел
            if (num % 2 == 1) {
                System.out.println(num);
            }
        }
    }

    public static void correctTest() {
        int[] numbers = {5, -5, 8, -8};

        for (int num : numbers) {
            if ((num & 1) == 1) {
                System.out.println(num);
            }
        }
    }
}