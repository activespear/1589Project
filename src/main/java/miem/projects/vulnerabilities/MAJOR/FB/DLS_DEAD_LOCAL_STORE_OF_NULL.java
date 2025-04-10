package miem.projects.vulnerabilities.MAJOR.FB;

public class DLS_DEAD_LOCAL_STORE_OF_NULL {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String str = "Hello, world!";
        // Присваивание null, но это значение не используется дальше
        str = null;
        // ...
    }

    public static void correctTest() {
        String str = "Hello, world!";
        System.out.println(str);
        // ...
    }
}