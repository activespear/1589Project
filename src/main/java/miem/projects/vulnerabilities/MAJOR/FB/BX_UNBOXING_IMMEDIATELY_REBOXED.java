package miem.projects.vulnerabilities.MAJOR.FB;

public class BX_UNBOXING_IMMEDIATELY_REBOXED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        Integer a = 10;
        // Анбоксинг → боксинг
        Integer b = Integer.valueOf(a.intValue());
        System.out.println(b);
    }

    public static void correctTest() {
        Integer a = 10;
        // Просто присваиваем, без лишних операций
        Integer b = a;
        System.out.println(b);
    }
}
