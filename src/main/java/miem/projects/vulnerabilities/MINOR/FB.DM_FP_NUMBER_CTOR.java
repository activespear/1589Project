package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DM_FP_NUMBER_CTOR {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Неэффективное создание объектов-обёрток через конструктор
        Integer i = new Integer(42);
        Double d = new Double(3.14);
        Long l = new Long(100L);
        
        System.out.println(i + ", " + d + ", " + l);
    }

    public static void correctTest() {
        // Использование valueOf() или автоупаковки
        Integer i = Integer.valueOf(42);
        Double d = 3.14;  // автоупаковка
        Long l = 100L;    // автоупаковка
        
        System.out.println(i + ", " + d + ", " + l);
    }
}