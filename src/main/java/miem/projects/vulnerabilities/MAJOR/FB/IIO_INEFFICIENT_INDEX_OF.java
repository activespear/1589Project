package miem.projects.vulnerabilities.MAJOR.FB;

public class IIO_INEFFICIENT_INDEX_OF {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String myString = "Hello, world!";

        // передача строки длиной 1 в String.indexOf()
        int index = myString.indexOf(".");
        System.out.println("Incorrect Result: " + index);
    }

    public static void correctTest() {
        String myString = "Hello, world!";

        // передача символа в String.indexOf()
        int index = myString.indexOf('.');
        System.out.println("Correct Result: " + index);
    }
}