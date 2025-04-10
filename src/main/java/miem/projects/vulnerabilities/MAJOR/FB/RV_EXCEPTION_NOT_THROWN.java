package miem.projects.vulnerabilities.MAJOR.FB;

public class RV_EXCEPTION_NOT_THROWN {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            IllegalAccessError error = new IllegalAccessError();
        } catch (Throwable e) {
            // ...
        }
    }

    public static void correctTest() {
        try {
            throw  new IllegalAccessError();
        } catch (Throwable e) {
            // ...
        }
    }
}