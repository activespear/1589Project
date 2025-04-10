package miem.projects.vulnerabilities.MAJOR.FB;

public class RCN_REDUNDANT_COMPARISON_TWO_NULL_VALUES {
    public static void main(String[] args) {
        incorrectTest("a", "b");
        correctTest("a", "b");
        incorrectTest(null, null);
    }

    public static void incorrectTest(Object a, Object b) {
        // Избыточное сравнение
        // (если метод всегда возвращает null, проверка бессмысленна)
        if (a == b) {
            System.out.println("Обе переменные равны null");
        }
    }

    public static void correctTest(Object a, Object b) {
        if (a == null) {
            System.out.println("Переменная a равна null");
        }
    }
}