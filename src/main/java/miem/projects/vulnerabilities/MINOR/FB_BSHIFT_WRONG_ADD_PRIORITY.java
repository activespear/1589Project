package miem.projects.vulnerabilities.MINOR.FB;

public class FB_BSHIFT_WRONG_ADD_PRIORITY {

    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int x = 1;
        int y = 2;
        // Некорректно: из-за приоритета операций получается x << (8 + y)
        int result = x << 8 + y;
        System.out.println("Incorrect result (x << 8 + y): " + result);
    }

    public static void correctTest() {
        int x = 1;
        int y = 2;
        // Корректно: скобки гарантируют правильный порядок операций (x << 8) + y
        int result = (x << 8) + y;
        System.out.println("Correct result ((x << 8) + y): " + result);
    }
}
