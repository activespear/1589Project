package miem.projects.vulnerabilities.MAJOR.FB;

public class ICAST_INTEGER_MULTIPLY_CAST_TO_LONG {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int a = 1000;
        int b = 2000;

        // результат умножения целых чисел приводится к типу long
        long result = (long) (a * b);
        System.out.println("Incorrect Result: " + result);
    }

    public static void correctTest() {
        int a = 1000;
        int b = 2000;

        // результат умножения целых чисел не нуждается в приведении к long
        long result = a * (long) b;
        System.out.println("Correct Result: " + result);
    }
}