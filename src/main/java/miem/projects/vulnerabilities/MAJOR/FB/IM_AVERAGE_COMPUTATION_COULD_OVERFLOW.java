package miem.projects.vulnerabilities.MAJOR.FB;

public class IM_AVERAGE_COMPUTATION_COULD_OVERFLOW {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int low = Integer.MAX_VALUE - 1;
        int high = Integer.MAX_VALUE;

        // может привести к переполнению
        int mid = (low + high) / 2;

        System.out.println("Incorrect mid: " + mid);
    }

    public static void correctTest() {
        int low = Integer.MAX_VALUE - 1;
        int high = Integer.MAX_VALUE;

        // предотвращает переполнение
        int mid = (low + high) >>> 1;

        System.out.println("Correct mid: " + mid);
    }
}