package miem.projects.vulnerabilities.MAJOR.FB;

public class DM_NEW_FOR_GETCLASS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Неоправданное создание объекта
        Class<?> clazz = new DM_NEW_FOR_GETCLASS().getClass();
        System.out.println("Class name: " + clazz.getName());
    }

    public static void correctTest() {
        Class<DM_NEW_FOR_GETCLASS> clazz = DM_NEW_FOR_GETCLASS.class;
        System.out.println("Class name: " + clazz.getName());
    }
}
