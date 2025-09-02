package miem.projects.vulnerabilities.MINOR;

public class REDUNDANT_NULL_CHECK {
    public static void main(String[] args) {
        Object obj = new Object();
        incorrectTest(obj);
        correctTest(obj);
    }

    // Потенциально небезопасное: избыточная проверка на null
    public static void incorrectTest(Object obj) {
        if (obj != null) {
            System.out.println(obj.hashCode());
            if (obj != null) {
                obj.toString();
            }
        }
    }

    // Корректная конструкция: одна проверка на null достаточно
    public static void correctTest(Object obj) {
        if (obj != null) {
            System.out.println(obj.hashCode());
            obj.toString();
        }
    }
}
