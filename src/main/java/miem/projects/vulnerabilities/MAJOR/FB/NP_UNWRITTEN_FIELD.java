package miem.projects.vulnerabilities.MAJOR.FB;

public class NP_UNWRITTEN_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Example {
            static String name;
        }
        String name = Example.name;
        // NullPointerException:
        System.out.println(name.length());
    }

    public static void correctTest() {
        class Example {
            static final String name = "default";
        }
        String name = Example.name;
        if (!name.isEmpty()) {
            System.out.println(name.length());
        }
    }
}