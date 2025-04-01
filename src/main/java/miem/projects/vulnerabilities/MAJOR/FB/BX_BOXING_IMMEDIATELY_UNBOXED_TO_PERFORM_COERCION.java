package miem.projects.vulnerabilities.MAJOR.FB;

public class BX_BOXING_IMMEDIATELY_UNBOXED_TO_PERFORM_COERCION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        double d = 42.5;

        // Боксинг Double, затем немедленный анбоксинг в int
        // deprecated since version 9
        Integer boxed = new Double(d).intValue();
        System.out.println(boxed);
    }

    public static void correctTest() {
        double d = 42.5;

        // Просто выполняем приведение типа
        int value = (int) d;
        System.out.println(value);
    }
}
