package miem.projects.vulnerabilities.MAJOR.FB;

public class INT_VACUOUS_BIT_OPERATION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int x = 42;
        // результат всегда x
        int y = x & 0xFFFFFFFF;
        int z = x | 0;
        int w = x ^ 0;

        System.out.println("x & 0xFFFFFFFF = " + y);
        System.out.println("x | 0 = " + z);
        System.out.println("x ^ 0 = " + w);
    }

    public static void correctTest() {
        int x = 42;
        int y = x & 0xFF;  // младший байт числа
        int z = x | 0xFF;  // устанавливаем младший байт в 1
        int w = x ^ 0xFF;  // инвертируем младший байт

        System.out.println("x & 0xFF = " + y);
        System.out.println("x | 0xFF = " + z);
        System.out.println("x ^ 0xFF = " + w);
    }
}