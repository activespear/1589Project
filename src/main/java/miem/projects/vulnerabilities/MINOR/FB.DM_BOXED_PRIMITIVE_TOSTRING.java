package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DM_BOXED_PRIMITIVE_TOSTRING {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        int number = 42;
        String str = new Integer(number).toString();  // Ненужное создание объекта-обёртки
        System.out.println(str);
    }

    public static void correctTest() {
        int number = 42;
        String str = Integer.toString(number);  // Прямой статический вызов
        System.out.println(str);
    }
}