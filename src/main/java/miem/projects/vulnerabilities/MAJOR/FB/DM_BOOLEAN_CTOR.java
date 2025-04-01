package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_BOOLEAN_CTOR {
    public static void main(String[] args) {
        //incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // deprecated since version 9
        Boolean bool1 = new Boolean(true);
        Boolean bool2 = new Boolean(false);

        System.out.println(bool1);
        System.out.println(bool2);
    }

    public static void correctTest() {
        // Правильное использование Boolean.valueOf()
        Boolean bool1 = Boolean.valueOf(true);
        // Автоупаковка true
        Boolean bool2 = true;

        System.out.println(bool1);
        System.out.println(bool2);
    }
}
