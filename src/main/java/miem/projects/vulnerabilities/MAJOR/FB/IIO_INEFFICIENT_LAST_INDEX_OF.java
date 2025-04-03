package miem.projects.vulnerabilities.MAJOR.FB;

public class IIO_INEFFICIENT_LAST_INDEX_OF {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String myString = "example.txt";

        // передача строки длиной 1 в lastIndexOf()
        int index = myString.lastIndexOf(".");
        System.out.println("Incorrect Result: " + index);
    }

    public static void correctTest() {
        String myString = "example.txt";

        // передача символа в lastIndexOf()
        int index = myString.lastIndexOf('.');
        System.out.println("Correct Result: " + index);
    }
}