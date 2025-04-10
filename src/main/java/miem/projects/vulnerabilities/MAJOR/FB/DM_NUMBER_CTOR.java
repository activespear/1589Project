package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_NUMBER_CTOR {
    public static void main(String[] args) {
        //incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Всегда создается новый объект
        // deprecated since version 9
        Integer a = new Integer(10);
        System.out.println(a);
    }

    public static void correctTest() {
        // Использует кэшированные объекты
        Integer a = Integer.valueOf(10);
        System.out.println(a);
    }
}
