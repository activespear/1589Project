package miem.projects.vulnerabilities.MAJOR.FB;

public class DLS_DEAD_LOCAL_STORE_SHADOWS_FIELD {
    private String field = "Поле класса";

    public static void main(String[] args) {
        DLS_DEAD_LOCAL_STORE_SHADOWS_FIELD obj = new DLS_DEAD_LOCAL_STORE_SHADOWS_FIELD();
        obj.incorrectTest();
        obj.correctTest();
    }

    public void incorrectTest() {
        // Локальная переменная с тем же именем, что и поле
        String field = "Локальная переменная";
        field = null;
        // ...
    }

    public void correctTest() {
        // Изменение поля класса напрямую
        this.field = "Новая строка";
        System.out.println(field);
    }
}