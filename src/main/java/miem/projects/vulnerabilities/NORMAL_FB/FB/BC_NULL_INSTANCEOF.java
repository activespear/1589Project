package miem.projects.vulnerabilities.NORMAL.FB;

public class BC_NULL_INSTANCEOF {
    public static void main(String[] args) {
        Object obj1 = "Hello";
        Object obj2 = null;

        incorrectTest(obj1);
        incorrectTest(obj2);

        correctTest(obj1);
        correctTest(obj2);
    }

    public static void incorrectTest(Object obj) {
        // Потенциально небезопасное: null instanceof String всегда false, может скрыть ошибку
        if (obj instanceof String) {
            System.out.println("Это строка");
        }
    }

    public static void correctTest(Object obj) {
        // Корректное использование: сначала проверяем на null
        if (obj != null && obj instanceof String) {
            System.out.println("Это строка");
        } else if (obj == null) {
            System.out.println("Объект равен null");
        }
    }
}