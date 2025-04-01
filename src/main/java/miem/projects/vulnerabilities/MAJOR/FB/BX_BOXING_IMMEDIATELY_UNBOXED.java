package miem.projects.vulnerabilities.MAJOR.FB;

public class BX_BOXING_IMMEDIATELY_UNBOXED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Боксинг и немедленный анбоксинг внутри метода
        int result = sum(10, 20);
        System.out.println(result);
    }

    public static void correctTest() {
        int result = sum(1, 2);
        System.out.println(result);
    }

    // Принимаем Integer вместо int
    public static int sum(Integer a, Integer b) {
        // Авто-распаковка
        return a + b;
    }
}
